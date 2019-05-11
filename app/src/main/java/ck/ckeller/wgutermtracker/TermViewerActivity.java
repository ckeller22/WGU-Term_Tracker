package ck.ckeller.wgutermtracker;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class TermViewerActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private Uri currentTermUri;
    private Term currentTerm;

    private int COURSE_LIST_ACTIVITY_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_viewer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        parseTerm();
        findTextViews();

    }

    public void openCourseList(View view) {
        Intent intent = new Intent(this, CourseListActivity.class);
        intent.putExtra(DataProvider.TERM_CONTENT_TYPE, currentTermUri);
        startActivityForResult(intent, COURSE_LIST_ACTIVITY_CODE);

    }

    public void findTextViews() {
        TextView tvName = findViewById(R.id.tv_term_name);
        TextView tvStart = findViewById(R.id.tv_term_start);
        TextView tvEnd = findViewById(R.id.tv_term_end);

        tvName.setText(currentTerm.getTermName());
        tvStart.setText(currentTerm.getTermStart());
        tvEnd.setText(currentTerm.getTermEnd());
    }

    public void parseTerm() {
        Intent intent = getIntent();
        currentTermUri = intent.getParcelableExtra(DataProvider.TERM_CONTENT_TYPE);

        long termId = Long.parseLong(currentTermUri.getLastPathSegment());
        currentTerm = DataManager.getTerm(this, termId);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_term_viewer, menu);
        return true;
    }
    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}
