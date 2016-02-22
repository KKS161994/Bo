package in.silive.bo;


import in.silive.bo.adapter.SubjectListAdapter;
import in.silive.bo.listener.RequestListener;
import in.silive.bo.model.Paper;
import in.silive.bo.utilities.Config;
import in.silive.bo.utilities.Controller;
import in.silive.bo.utilities.MyUtilities;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.StandardExceptionParser;
import com.google.android.gms.analytics.Tracker;

import com.google.android.gms.analytics.GoogleAnalytics;


public class MainActivity extends FragmentActivity implements RequestListener,
		OnClickListener, TextWatcher, OnItemClickListener {

	private ImageView crossImgView;
	private EditText searchEdtView;
	private ListView subjectListView;

	private ProgressBar mProgressBar;
	private Button retryBtn;
	private TextView noResultTxtView, noInternetTxtView;
	private View searchView;
	private SubjectListAdapter subListAdapter;
	private ArrayList<Paper> papers = new ArrayList<>();
	GoogleAnalytics tracker;
	Tracker t;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		t = ((BytepadApplication) MainActivity.this.getApplication()).getTracker(
				BytepadApplication.TrackerName.APP_TRACKER);
		t.enableAdvertisingIdCollection(true);
		t.setScreenName("Home");
		t.send(new HitBuilders.AppViewBuilder().build());
	try {
	crossImgView = (ImageView) findViewById(R.id.cross_img);
	searchEdtView = (EditText) findViewById(R.id.search_txt);
	subjectListView = (ListView) findViewById(R.id.subject_list);
	mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
	retryBtn = (Button) findViewById(R.id.retry_btn);
	noResultTxtView = (TextView) findViewById(R.id.no_result_txt);
	noInternetTxtView = (TextView) findViewById(R.id.no_internet_txt);
	searchView = findViewById(R.id.search_container);
	searchEdtView.addTextChangedListener(this);

	crossImgView.setOnClickListener(this);

	retryBtn.setOnClickListener(this);

	// ENTER on Keyboard means SEARCH
	searchEdtView.setOnKeyListener(new OnKeyListener() {

		@Override
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			switch (keyCode) {
				case KeyEvent.KEYCODE_DPAD_CENTER:
				case KeyEvent.KEYCODE_ENTER:
					onClick(v);
					return true;
				default:
					break;
			}
			return false;
		}
	});

	doInternetConnBasedAction();

	subListAdapter = new SubjectListAdapter(this, papers);
	subjectListView.setAdapter(subListAdapter);
	subjectListView.setOnItemClickListener(this);
	Controller.setRequestListener(this);
}
catch (Exception e){
	t.send(new HitBuilders.ExceptionBuilder()
					.setDescription(
							new StandardExceptionParser(this, null)
									.getDescription(Thread.currentThread().getName(), e))
					.setFatal(false)
					.build()
	);
}
	}
	@Override
		    protected void onStart() {
		        // TODO Auto-generated method stub
		        super.onStart();
		        GoogleAnalytics.getInstance(MainActivity.this).reportActivityStart(this);
		    }


		    @Override
		    protected void onStop() {
		        // TODO Auto-generated method stub
		        super.onStop();
		        GoogleAnalytics.getInstance(MainActivity.this).reportActivityStop(this);
		    }
	@Override
	public void preRequest() {
		hideListView();
		showProgressBar();
	}

	@Override
	public void postRequest(String result) {
		hideProgressBar();
		showListView();
		papers.clear();
		try {
			JSONArray jsonArr = new JSONArray(result);
			if (jsonArr.length() == 0) {
				showNoResultView();
				return;
			}
			Log.d("Response", "length : " + jsonArr.length());
			for (int i = 0; i < jsonArr.length(); i = i + 1) {
				papers.add(new Paper(jsonArr.getJSONObject(i)));
			}
			subListAdapter.notifyDataSetChanged();
		} catch (JSONException ex) {
			showNoInternetView();
			ex.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();

		switch (id) {
		case R.id.retry_btn:
			hideNoInternetView();
			doInternetConnBasedAction();
		case R.id.search_txt:
			String queryStr = searchEdtView.getText().toString()
					.replace(" ", "%20");
			if (queryStr.length() == 0) {
				Toast.makeText(this, "Please enter a valid query",
						Toast.LENGTH_SHORT).show();
			} else {
				hideNoResultView();
				showProgressBar();
				(new Controller()).execute(Config.PAPERS_URL + queryStr);

			}
			break;
		case R.id.cross_img:
			searchEdtView.setText("");
			break;
		}
	}

	private void doInternetConnBasedAction() {
		if (!MyUtilities.isInternetConnected(this)) {
			showNoInternetView();
		} else {
			showSearchView();
		}
	}

	private void showSearchView() {
		searchView.setVisibility(View.VISIBLE);
	}

	private void hideSearchView() {
		searchView.setVisibility(View.GONE);
	}

	private void showProgressBar() {
		mProgressBar.setVisibility(View.VISIBLE);
	}

	private void hideProgressBar() {
		mProgressBar.setVisibility(View.GONE);
	}

	private void showNoResultView() {
		noResultTxtView.setVisibility(View.VISIBLE);
	}

	private void hideNoResultView() {
		noResultTxtView.setVisibility(View.GONE);
	}

	private void showListView() {
		subjectListView.setVisibility(View.VISIBLE);
	}

	private void hideListView() {
		subjectListView.setVisibility(View.GONE);
	}

	private void showNoInternetView() {
		noInternetTxtView.setVisibility(View.VISIBLE);
		retryBtn.setVisibility(View.VISIBLE);
	}

	private void hideNoInternetView() {
		noInternetTxtView.setVisibility(View.GONE);
		retryBtn.setVisibility(View.GONE);
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		if (s.length() > 0)
			crossImgView.setVisibility(View.VISIBLE);
		else
			crossImgView.setVisibility(View.GONE);
	}

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		t.send(new HitBuilders.EventBuilder()
				.setCategory("Tracks")
				.setLabel("FIle Download")
				.build());
		(new ConfirmationDialog(view)).show(getSupportFragmentManager(),
				"Confirmation Dilaog");
	}
}
