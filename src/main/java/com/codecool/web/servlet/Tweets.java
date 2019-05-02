package com.codecool.web.servlet;

import com.codecool.web.model.Tweet;
import com.codecool.web.service.TweetService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/tweets"})
public class Tweets extends HttpServlet {
    private List<Tweet> tweetList = new ArrayList<>();
    private TweetService tweetService = new TweetService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        tweetList = tweetService.getTweetList();
        req.setAttribute("tweetList", tweetList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("tweets.jsp");
        requestDispatcher.forward(req, resp);
    }
}
