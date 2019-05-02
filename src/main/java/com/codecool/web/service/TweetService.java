package com.codecool.web.service;

import com.codecool.web.model.Tweet;

import java.util.List;

public class TweetService {
    private XMLService xmlService;

    public TweetService() {
        this.xmlService = new XMLService();
    }

    public List<Tweet> getTweetList() {
        return xmlService.readTweets();
    }

    public void addTweet(Tweet tweet) {
        xmlService.writeTweet(tweet);
    }
}
