package com.healthkart.newsreaderapplication.Model;
/**@author Neelam Kushwah
 * @ date 09-10-2015**/
public class NewsModel {
	public String display_title;
	public String byline;
	public String summary_short;
	public String publication_date;
	public String movieid;
	public String image;

	public String getDisplay_title() {
		return display_title;
	}

	public void setDisplay_title(String display_title) {
		this.display_title = display_title;
	}

	public String getByline() {
		return byline;
	}

	public void setByline(String byline) {
		this.byline = byline;
	}

	public String getSummary_short() {
		return summary_short;
	}

	public void setSummary_short(String summary_short) {
		this.summary_short = summary_short;
	}

	public String getPublication_date() {
		return publication_date;
	}

	public void setPublication_date(String publication_date) {
		this.publication_date = publication_date;
	}

	public String getMovieid() {
		return movieid;
	}

	public void setMovieid(String movieid) {
		this.movieid = movieid;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
