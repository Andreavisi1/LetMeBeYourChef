package com.example.letmebeyourchef.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letmebeyourchef.Models.ResponseFromApiIstruzioni;
import com.example.letmebeyourchef.R;

import java.util.List;

public class IstruzioniAdapter extends RecyclerView.Adapter<IstruzioniViewHolder> {
   Context context;
   List<ResponseFromApiIstruzioni> list;

   public IstruzioniAdapter(Context context, List<ResponseFromApiIstruzioni> list) {
      this.context = context;
      this.list = list;
   }

   @NonNull
   @Override
   public IstruzioniViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      return new IstruzioniViewHolder(LayoutInflater.from(context).inflate(R.layout.list_istruzioni, parent, false));

   }

   @Override
   public void onBindViewHolder(@NonNull IstruzioniViewHolder holder, int position) {

      holder.textView_nome_istruzione.setText(list.get(position).name);
      holder.recycler_step_istruzione.setHasFixedSize(true);
      holder.recycler_step_istruzione.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
      IstruzioniStepAdapter istruzioniStepAdapter = new IstruzioniStepAdapter(context, list.get(position).steps);
      holder.recycler_step_istruzione.setAdapter(istruzioniStepAdapter);

   }

   @Override
   public int getItemCount() {

      return list.size();
   }
}

class IstruzioniViewHolder extends RecyclerView.ViewHolder {
   TextView textView_nome_istruzione;
   RecyclerView recycler_step_istruzione;

   public IstruzioniViewHolder(@NonNull View itemView) {
      super(itemView);
      textView_nome_istruzione = itemView.findViewById(R.id.textView_nome_istruzione);
      recycler_step_istruzione = itemView.findViewById(R.id.recycler_step_istruzione);
   }
}

