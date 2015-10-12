package com.healthkart.newsreaderapplication.adaptor;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cuubonandroid.sugaredlistanimations.GPlusListAdapter;
import com.cuubonandroid.sugaredlistanimations.SpeedScrollListener;
import com.healthkart.newsreaderapplication.Constants;
import com.healthkart.newsreaderapplication.R;
import com.healthkart.newsreaderapplication.Model.NewsModel;
import com.healthkart.newsreaderapplication.Utility.SharePreferanceWrapperSingleton;

/**
 * @author Neelam Kushwah
 * @date 09-10-2015
 **/
public class NewsAdapter extends GPlusListAdapter {

	private LayoutInflater layoutInflater;
	private HolderListItem holderListItem;
	private Activity activity;
	private Context context;
	private View view;
	private SharePreferanceWrapperSingleton objSPS;
	private Typeface font;
	private ArrayList<NewsModel> arr;
	private ArrayList<String> arrNumber;

	// Constructor
	public NewsAdapter(Context context, ArrayList<NewsModel> arr,
			SharePreferanceWrapperSingleton objSPS, Typeface font,
			SpeedScrollListener scrollListener, ArrayList<String> arrNumber) {
		super(context, scrollListener, arrNumber);

		this.arr = arr;
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.objSPS = objSPS;
		this.context = context;
		this.arrNumber = arrNumber;
		this.scrollListener = scrollListener;
		this.font = font;
		this.objSPS = objSPS;

	}

	static class HolderListItem {
		private TextView tv_Title, tv_TitleValue, tv_MovieId, tv_MovieIdValue,
				tv_Author, tv_AuthorValue, tv_Date, tv_DateValue, tv_Detail;
		private RelativeLayout rl_Group, rl_Child, rlTitle, rlMovie, rlAuthor,
				rlDate, rlSeeDetail;
		private ImageView iv_SeeDetail;

		private View view, view1, view2;
	}

	/**
	 * Function Name : 1. (Overridden Function) getCount
	 * 
	 * 2.Function Description : It returns the size of array
	 */
	public int getCount() {
		return arr.size();
	}

	/**
	 * Function Name : 1. (Overridden Function) getItem
	 */
	public Object getItem(int arg0) {
		return arr.get(arg0);
	}

	/**
	 * Function Name : 1.(Overridden Function) getItem 2.Function Description :
	 * It returns the Position of array
	 */
	public long getItemId(int position) {
		return position;
	}

	

	private void setData(int position) {
		// apply null checks and set data
		if ((!"".equals(arr.get(position).getByline()) && (arr.get(position)
				.getByline() != null))) {
			holderListItem.tv_AuthorValue
					.setText(arr.get(position).getByline());
		} else {
			holderListItem.tv_AuthorValue.setVisibility(View.INVISIBLE);

		}
		if ((!"".equals(arr.get(position).getDisplay_title()) && (arr.get(
				position).getDisplay_title() != null))) {
			holderListItem.tv_TitleValue.setText(arr.get(position)
					.getDisplay_title());
		} else {
			holderListItem.tv_TitleValue.setVisibility(View.INVISIBLE);

		}
		if ((!"".equals(arr.get(position).getMovieid()) && (arr.get(position)
				.getMovieid() != null))) {
			holderListItem.tv_MovieIdValue.setText(arr.get(position)
					.getMovieid());
		} else {
			holderListItem.tv_MovieIdValue.setVisibility(View.INVISIBLE);

		}
		if ((!"".equals(arr.get(position).getPublication_date()) && !(arr.get(
				position).getPublication_date().equals(null)))) {
			holderListItem.tv_DateValue.setText(arr.get(position)
					.getPublication_date());
		} else {
			holderListItem.tv_DateValue.setVisibility(View.INVISIBLE);

		}

	}

	@Override
	protected View getRowView(int position, View convertView, ViewGroup parent) {
		view = convertView;
		if (view == null) {
			view = layoutInflater.inflate(R.layout.layout_news_listitem, null);
			holderListItem = new HolderListItem();
			//rersolve id
			holderListItem.iv_SeeDetail = (ImageView) view
					.findViewById(R.id.iv_SeeDetail);
			holderListItem.rl_Group = (RelativeLayout) view
					.findViewById(R.id.rl_Group);
			holderListItem.rlSeeDetail = (RelativeLayout) view
					.findViewById(R.id.rlSeeDetail);
			holderListItem.rl_Child = (RelativeLayout) view
					.findViewById(R.id.rl_Child);
			holderListItem.rlTitle = (RelativeLayout) view
					.findViewById(R.id.rlTitle);
			holderListItem.rlMovie = (RelativeLayout) view
					.findViewById(R.id.rlMovie);
			holderListItem.rlAuthor = (RelativeLayout) view
					.findViewById(R.id.rlAuthor);
			holderListItem.rlDate = (RelativeLayout) view
					.findViewById(R.id.rlDate);
			holderListItem.tv_Detail = (TextView) view
					.findViewById(R.id.tv_Detail);
			holderListItem.view = (View) view.findViewById(R.id.view);
			holderListItem.view1 = (View) view.findViewById(R.id.view1);
			holderListItem.view2 = (View) view.findViewById(R.id.view2);
			holderListItem.tv_TitleValue = (TextView) view
					.findViewById(R.id.tv_TitleValue);
			holderListItem.tv_MovieId = (TextView) view
					.findViewById(R.id.tv_MovieId);
			holderListItem.tv_MovieIdValue = (TextView) view
					.findViewById(R.id.tv_MovieIdValue);
			holderListItem.tv_Author = (TextView) view
					.findViewById(R.id.tv_Author);
			holderListItem.tv_AuthorValue = (TextView) view
					.findViewById(R.id.tv_AuthorValue);
			holderListItem.tv_Title = (TextView) view
					.findViewById(R.id.tv_Title);
			holderListItem.tv_Date = (TextView) view.findViewById(R.id.tv_Date);
			holderListItem.tv_DateValue = (TextView) view
					.findViewById(R.id.tv_DateValue);
			// textsize
			holderListItem.tv_Title.setTextSize(TypedValue.COMPLEX_UNIT_PX,
					objSPS.getValueFromSharedPref(Constants.TEXT_SIZE));
			holderListItem.tv_MovieId.setTextSize(TypedValue.COMPLEX_UNIT_PX,
					objSPS.getValueFromSharedPref(Constants.TEXT_SIZE));
			holderListItem.tv_MovieIdValue.setTextSize(
					TypedValue.COMPLEX_UNIT_PX,
					objSPS.getValueFromSharedPref(Constants.TEXT_SIZE));
			holderListItem.tv_Author.setTextSize(TypedValue.COMPLEX_UNIT_PX,
					objSPS.getValueFromSharedPref(Constants.TEXT_SIZE));
			holderListItem.tv_AuthorValue.setTextSize(
					TypedValue.COMPLEX_UNIT_PX,
					objSPS.getValueFromSharedPref(Constants.TEXT_SIZE));
			holderListItem.tv_TitleValue.setTextSize(
					TypedValue.COMPLEX_UNIT_PX,
					objSPS.getValueFromSharedPref(Constants.TEXT_SIZE));
			holderListItem.tv_Detail.setTextSize(TypedValue.COMPLEX_UNIT_PX,
					objSPS.getValueFromSharedPref(Constants.TEXT_SIZE));
			holderListItem.tv_Date.setTextSize(TypedValue.COMPLEX_UNIT_PX,
					objSPS.getValueFromSharedPref(Constants.TEXT_SIZE));
			holderListItem.tv_DateValue.setTextSize(TypedValue.COMPLEX_UNIT_PX,
					objSPS.getValueFromSharedPref(Constants.TEXT_SIZE));
			// padding
			holderListItem.rl_Group.setPadding(
					objSPS.getValueFromSharedPref(Constants.PADDING_LEFT),
					objSPS.getValueFromSharedPref(Constants.PADDING_TOP) / 2,
					objSPS.getValueFromSharedPref(Constants.PADDING_LEFT),
					objSPS.getValueFromSharedPref(Constants.PADDING_TOP) / 2);
			holderListItem.iv_SeeDetail.setPadding(0, 0,
					objSPS.getValueFromSharedPref(Constants.PADDING_LEFT), 0);
			holderListItem.rlTitle.setPadding(
					objSPS.getValueFromSharedPref(Constants.PADDING_LEFT),
					objSPS.getValueFromSharedPref(Constants.PADDING_TOP),
					objSPS.getValueFromSharedPref(Constants.PADDING_LEFT),
					objSPS.getValueFromSharedPref(Constants.PADDING_TOP));
			holderListItem.rlMovie.setPadding(
					objSPS.getValueFromSharedPref(Constants.PADDING_LEFT),
					objSPS.getValueFromSharedPref(Constants.PADDING_TOP),
					objSPS.getValueFromSharedPref(Constants.PADDING_LEFT),
					objSPS.getValueFromSharedPref(Constants.PADDING_TOP));
			holderListItem.rlAuthor.setPadding(
					objSPS.getValueFromSharedPref(Constants.PADDING_LEFT),
					objSPS.getValueFromSharedPref(Constants.PADDING_TOP),
					objSPS.getValueFromSharedPref(Constants.PADDING_LEFT),
					objSPS.getValueFromSharedPref(Constants.PADDING_TOP));
			holderListItem.rlDate.setPadding(
					objSPS.getValueFromSharedPref(Constants.PADDING_LEFT),
					objSPS.getValueFromSharedPref(Constants.PADDING_TOP),
					objSPS.getValueFromSharedPref(Constants.PADDING_LEFT),
					objSPS.getValueFromSharedPref(Constants.PADDING_TOP));

			// set font
			holderListItem.tv_TitleValue.setTypeface(font, Typeface.BOLD);
			holderListItem.tv_Title.setTypeface(font, Typeface.BOLD);
			holderListItem.tv_MovieIdValue.setTypeface(font, Typeface.BOLD);
			holderListItem.tv_AuthorValue.setTypeface(font, Typeface.BOLD);
			holderListItem.tv_DateValue.setTypeface(font, Typeface.BOLD);
			holderListItem.tv_Detail.setTypeface(font, Typeface.BOLD);

			holderListItem.tv_MovieId.setTypeface(font, Typeface.BOLD);
			holderListItem.tv_Author.setTypeface(font, Typeface.BOLD);
			holderListItem.tv_Date.setTypeface(font, Typeface.BOLD);

			// height width

			holderListItem.rl_Child.getLayoutParams().height = (int) (objSPS
					.getValueFromSharedPref(Constants.SCREEN_HEIGHT) * .35);
			holderListItem.view.getLayoutParams().height = (int) (objSPS
					.getValueFromSharedPref(Constants.SCREEN_HEIGHT) * .003);
			holderListItem.view1.getLayoutParams().height = (int) (objSPS
					.getValueFromSharedPref(Constants.SCREEN_HEIGHT) * .003);
			holderListItem.view2.getLayoutParams().height = (int) (objSPS
					.getValueFromSharedPref(Constants.SCREEN_HEIGHT) * .003);
			holderListItem.iv_SeeDetail.getLayoutParams().height = (int) (objSPS
					.getValueFromSharedPref(Constants.SCREEN_HEIGHT) * .032);
			holderListItem.iv_SeeDetail.getLayoutParams().width = (int) (objSPS
					.getValueFromSharedPref(Constants.SCREEN_HEIGHT) * .032);

			view.setTag(holderListItem);
		} else {

			holderListItem = (HolderListItem) view.getTag();
		}

		// set data
		setData(position);

		return view;

	}

}
