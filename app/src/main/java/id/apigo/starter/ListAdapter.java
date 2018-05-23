package id.apigo.starter;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.apigo.ApigoObject;

/**
 * Created by Apigo on 02/10/17.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<ApigoObject> list;

    public ListAdapter() {
        this.list = new ArrayList<>();
    }

    public void replaceData(List<ApigoObject> list) {
        if (!this.list.isEmpty()) {
            this.list.clear();
        }
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_1, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ApigoObject object = this.list.get(position);
        JSONObject json = new JSONObject(object.dataSet());
        String data;
        try {
            data = json.toString(2);
        } catch (JSONException e) {
            data = json.toString();
        }
        holder.label.setTypeface(Typeface.MONOSPACE);
        holder.label.setTextSize(10.0f);
        holder.label.setText(data);
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView label;

        ViewHolder(View itemView) {
            super(itemView);

            label = itemView.findViewById(android.R.id.text1);
        }
    }
}
