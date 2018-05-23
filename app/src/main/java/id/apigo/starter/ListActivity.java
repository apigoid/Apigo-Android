package id.apigo.starter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mesosfer.FindCallback;
import com.mesosfer.MesosferException;
import com.mesosfer.MesosferQuery;

import java.util.List;

import id.apigo.ApigoObject;

public class ListActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Param param = getIntent().getParcelableExtra(Param.class.getSimpleName());

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(param.getaClass().getSimpleName());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        progressBar = findViewById(R.id.progressBar);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        final ListAdapter adapter = new ListAdapter();
        recyclerView.setAdapter(adapter);

        progressBar.setVisibility(View.VISIBLE);
        //noinspection unchecked
        MesosferQuery.getQuery(param.getaClass()).findInBackground(new FindCallback<ApigoObject>() {
            @Override
            public void done(List<ApigoObject> objects, MesosferException e) {
                progressBar.setVisibility(View.GONE);
                if (e != null) {
                    Toast.makeText(ListActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    return;
                }

                adapter.replaceData(objects);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
