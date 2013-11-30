package io.github.rssdroid.domain;

public class FeedUrl {

    private String feedUrl;
    private String feedDescription;

    public FeedUrl() {
    }

    public FeedUrl(String feedDescription, String feedUrl) {
        this.feedDescription = feedDescription;
        this.feedUrl = feedUrl;
    }

    public String getFeedDescription() {
        return feedDescription;
    }

    public void setFeedDescription(String feedDescription) {
        this.feedDescription = feedDescription;
    }

    public String getFeedUrl() {
        return feedUrl;
    }

    public void setFeedUrl(String feedUrl) {
        this.feedUrl = feedUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FeedUrl)) return false;

        FeedUrl feedUrl1 = (FeedUrl) o;

        if (!feedDescription.equals(feedUrl1.feedDescription)) return false;
        if (!feedUrl.equals(feedUrl1.feedUrl)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = feedUrl.hashCode();
        result = 31 * result + feedDescription.hashCode();
        return result;
    }
}
