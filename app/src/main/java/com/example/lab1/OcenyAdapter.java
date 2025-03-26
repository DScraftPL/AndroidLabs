package com.example.lab1;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OcenyAdapter extends RecyclerView.Adapter<OcenyAdapter.OcenyViewHolder> {

    public List<OcenyDane> oceny;

    public OcenyAdapter(List<OcenyDane> oceny) {
        this.oceny = oceny;
    }

    @NonNull
    @Override
    public OcenyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.oceny_element, parent, false);

        return new OcenyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OcenyAdapter.OcenyViewHolder holder, int position) {
        OcenyDane ocena = oceny.get(position);
        holder.przedmiot.setText(ocena.getNazwaOcena());

        holder.grupaOcen.setOnCheckedChangeListener(null);
        holder.ocena2.setChecked(true);
        ocena.setCurrentOcena(Ocena.OCENA2);

        if (ocena.getCurrentOcena() != null) {
            switch (ocena.getCurrentOcena()) {
                case OCENA2:
                    holder.ocena2.setChecked(true);
                    break;
                case OCENA3:
                    holder.ocena3.setChecked(true);
                    break;
                case OCENA35:
                    holder.ocena35.setChecked(true);
                    break;
                case OCENA4:
                    holder.ocena4.setChecked(true);
                    break;
                case OCENA45:
                    holder.ocena45.setChecked(true);
                    break;
                case OCENA5:
                    holder.ocena5.setChecked(true);
                    break;
            }
        }

        holder.grupaOcen.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.ocena2) {
                    ocena.setCurrentOcena(Ocena.OCENA2);
                } else if (checkedId == R.id.ocena3) {
                    ocena.setCurrentOcena(Ocena.OCENA3);
                } else if (checkedId == R.id.ocena35) {
                    ocena.setCurrentOcena(Ocena.OCENA35);
                } else if (checkedId == R.id.ocena4) {
                    ocena.setCurrentOcena(Ocena.OCENA4);
                } else if (checkedId == R.id.ocena45) {
                    ocena.setCurrentOcena(Ocena.OCENA45);
                } else if (checkedId == R.id.ocena5) {
                    ocena.setCurrentOcena(Ocena.OCENA5);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return oceny.size();
    }

    public class OcenyViewHolder extends RecyclerView.ViewHolder implements RadioGroup.OnCheckedChangeListener {
        public TextView przedmiot;
        public RadioGroup grupaOcen;
        public RadioButton ocena2;
        public RadioButton ocena3;
        public RadioButton ocena35;
        public RadioButton ocena4;
        public RadioButton ocena45;
        public RadioButton ocena5;

        public OcenyViewHolder(@NonNull View itemView) {
            super(itemView);
            przedmiot = itemView.findViewById(R.id.przedmiot);
            grupaOcen = itemView.findViewById(R.id.grupaOcen);
            ocena2 = itemView.findViewById(R.id.ocena2);
            ocena3 = itemView.findViewById(R.id.ocena3);
            ocena35 = itemView.findViewById(R.id.ocena35);
            ocena4 = itemView.findViewById(R.id.ocena4);
            ocena45 = itemView.findViewById(R.id.ocena45);
            ocena5 = itemView.findViewById(R.id.ocena5);
        }

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

        }
    }

}
