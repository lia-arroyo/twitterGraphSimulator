package nz.ac.auckland.se281.a4;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import nz.ac.auckland.se281.a4.ds.Graph;
import nz.ac.auckland.se281.a4.ds.Node;

//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//*******************************
public class TweetGraph extends Graph {

	protected List<Tweet> tweets;
	// Change this to map
	protected Map<TwitterHandle, List<Tweet>> nodeTweets;

	public TweetGraph(List<String> edges, List<Tweet> tweets, Map<TwitterHandle, List<Tweet>> map) throws Exception {
		super(edges);
		this.tweets = tweets;
		// changed to LinkedHashMap for fixed order
		this.nodeTweets = new LinkedHashMap<>();
		nodeTweets = map;
	}

	public List<Tweet> getTweets(Node n) {
		return nodeTweets.get(n);
	}

	public List<String> getTweetsTexts(TwitterHandle n) {
		List<String> texts = new ArrayList<>(); // Only allowed to use ArrayList HERE !!!
		for (Tweet t : getTweets(n)) {
			texts.add(t.getTextString());
		}
		return texts;
	}

	/**
	 * search for a keyword in a tweet starting from a given node
	 * 
	 * @param user         the twitter user
	 * @param tweetKeyword the keyword we are searching for
	 * @return tweet containing keyword if it exists, null otherwise
	 */
	public String searchTweet(TwitterHandle user, String tweetKeyword) {

		// getting a list of all successor nodes
		List<Node<String>> successorNodes = depthFirstSearch(user, true);

		// iterating through each successor node
		for (Node<String> successor : successorNodes) {

			// converting successor to a twitter handle
			TwitterHandle successorHandle = (TwitterHandle) successor;

			// getting a list of tweets from successor
			List<String> listOfAllTweets = getTweetsTexts(successorHandle);

			// iterating through each tweet
			for (String tweet : listOfAllTweets) {

				// checking if tweet contains keyword
				if (tweet.contains(tweetKeyword)) {
					return "The tweet string found is: " + tweet + "\nUser " + successorHandle.getName() + " {"
							+ successorHandle.getID() + "} tweeted " + tweetKeyword;
				}
			}

		}

		// returning this message when no tweet is found
		return "No successor of " + user.getName() + "tweeted " + tweetKeyword;

	}
}
