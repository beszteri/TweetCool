package com.codecool.web.servlet;

import com.codecool.web.model.Tweet;
import com.codecool.web.service.TweetService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/new-tweet"})
public class NewTweetServlet extends HttpServlet {
    private TweetService tweetService = new TweetService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int tweetId = tweetService.getTweetList().size() + 1;
        String poster = req.getParameter("poster");
        String content = req.getParameter("content");
        Tweet newTweet = new Tweet(tweetId, poster, content);
        tweetService.addTweet(newTweet);
        resp.sendRedirect("index.html");
    }
}
