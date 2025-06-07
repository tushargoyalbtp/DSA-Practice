package StackOverFlowDesign;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Answer implements Votable, Commentable {
    private final int id;
    private final String content;
    private final User author;
    private final Question question;
    private boolean isAccepted;
    private final Date creationDate;
    private final List<Comment> comments;
    private final List<Vote> votes;

    public Answer(User author, Question question, String content) {
        this.id = generateId();
        this.author = author;
        this.question = question;
        this.content = content;
        this.votes = new ArrayList<>();
        this.creationDate = new Date();
        this.comments = new ArrayList<>();
        this.isAccepted = false;
    }


    /**
     * @param comment
     */
    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    /**
     * @return
     */
    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    /**
     * @param voter
     * @param type
     */
    @Override
    public void vote(User voter, VoteType type) {
        votes.removeIf(v -> v.getVoter().equals(voter));
        votes.add(new Vote(voter, type));
        author.updateReputation(10 * (type == VoteType.UPVOTE ? 1 : -1));  // +10 for upvote, -10 for downvote
    }

    /**
     * @return
     */
    @Override
    public int getVoteCount() {
        return votes.stream().mapToInt(v -> v.getType() == VoteType.UPVOTE ? 1 : -1).sum();
    }

    public void markAsAccepted() {
        if (isAccepted) {
            throw new IllegalStateException("This answer is already accepted");
        }
        isAccepted = true;
        author.updateReputation(15);
    }

    private int generateId() {
        return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public User getAuthor() {
        return author;
    }

    public Question getQuestion() {
        return question;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public List<Vote> getVotes() {
        return votes;
    }
}
