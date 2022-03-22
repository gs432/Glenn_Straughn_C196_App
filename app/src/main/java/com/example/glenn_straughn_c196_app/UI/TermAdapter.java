package com.example.glenn_straughn_c196_app.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glenn_straughn_c196_app.Entities.Term;
import com.example.glenn_straughn_c196_app.R;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {



    class TermViewHolder extends RecyclerView.ViewHolder{

        private final TextView termItemView;

        private TermViewHolder(View itemView){

            super(itemView);
            termItemView = itemView.findViewById(R.id.textView10);
            itemView.setOnClickListener(view -> {

                int position=getAdapterPosition();
                final Term current= rTermList.get(position);

                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                Intent intent=new Intent(context,TermList.class);
                intent.putExtra("termId", current.getTermId());
                intent.putExtra("termName", current.getTermName());
                intent.putExtra("termStart", current.getTermStart());
                intent.putExtra("termEnd", current.getTermEnd());
                context.startActivity(intent);
            });
        }
    }

    private List<Term> rTermList;
    private final Context context;
    private final LayoutInflater rInflater;

    public TermAdapter(Context context){
        rInflater=LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public TermAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = rInflater.inflate(R.layout.term_list_item,parent,false);
        return new TermViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermAdapter.TermViewHolder holder, int position) {
        if (rTermList != null){
            Term current = rTermList.get(position);
            String name = current.getTermName();
            holder.termItemView.setText(name);
        } else {
            holder.termItemView.setText("No Term Name");
        }
    }

    public void setTerms(List<Term> allTerms) {
        rTermList = allTerms;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (rTermList != null) {
            return rTermList.size();
        }
        else return 0;
    }
}
