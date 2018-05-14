package com.app.sorge.todolist.view;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sorge.todolist.R;
import com.app.sorge.todolist.dependencyinjection.PlanApplication;
import com.app.sorge.todolist.dependencyinjection.ViewModelFactory;
import com.app.sorge.todolist.domain.Plan;
import com.app.sorge.todolist.viewmodel.ListActivityViewModel;

import java.util.List;

import javax.inject.Inject;

public class ListActivity extends LifecycleActivity {

    private ListActivityViewModel listActivityViewModel;
    private RecyclerAdapter adapter;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    @Inject
    ViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getIDS();
        setupRecycler();
        setupViewModel();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder=new AlertDialog.Builder(ListActivity.this);
                final View mView=getLayoutInflater().inflate(R.layout.enter_plan_dialog,null);
                final EditText etxtTitle=(EditText)mView.findViewById(R.id.etxtTitle);
                final EditText etxtDescription=(EditText)mView.findViewById(R.id.etxtDescription);
                final EditText etxtDate=(EditText)mView.findViewById(R.id.etxtDate);
                Button btnAdd=(Button)mView.findViewById(R.id.btnAdd);
                mBuilder.setView(mView);
                final AlertDialog dialog=mBuilder.create();
                dialog.show();
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(etxtTitle.getText().length()==0){
                            Toast.makeText(getApplicationContext(),"Please enter the title!",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            if(etxtTitle.getText().length()>15) {

                                Toast.makeText(getApplicationContext(),"The title is too long!",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                String title, description, date;
                                title = etxtTitle.getText().toString();
                                description = etxtDescription.getText().toString();
                                date = etxtDate.getText().toString();
                                Plan plan = new Plan(title, description, date);
                                listActivityViewModel.addPlan(plan);
                                dialog.hide();
                            }
                        }
                    }
                });



            }
        });

        listActivityViewModel.getPlans().observe(ListActivity.this, new Observer<List<Plan>>() {
            @Override
            public void onChanged(@Nullable List<Plan> plans) {
                setupAdapter(plans);

            }
        });



    }

    private void getIDS(){
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButton);
    }
    private void setupViewModel(){
        ((PlanApplication) getApplication()).getApplicationComponent().inject(this);
        listActivityViewModel = ViewModelProviders.of(this, viewModelFactory).get(ListActivityViewModel.class);
    }

    private void setupRecycler(){

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupAdapter(List<Plan> plans){
        adapter=new RecyclerAdapter(plans);
        recyclerView.setAdapter(adapter);
    }


    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.PlanViewHolder> {

        private List<Plan> list;

        public RecyclerAdapter(List<Plan> list) {
            this.list = list;
        }

        @Override
        public PlanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           View view= LayoutInflater.from(parent.getContext())
                   .inflate(R.layout.list_item,parent,false);
           return new PlanViewHolder(view);
        }

        @Override
        public void onBindViewHolder(PlanViewHolder holder, int position) {
            Plan plan=list.get(position);
            holder.txtTitle.setText(plan.getTitle());
            holder.txtDate.setText(plan.getDate());

            String fletter;
            if(Character.isLetter(plan.getTitle().charAt(0))) {
                fletter = String.valueOf(plan.getTitle().charAt(0));
                fletter = fletter.toLowerCase();
            }
            else fletter="a";

            int resID = getResources().getIdentifier(fletter , "drawable", getPackageName());
            holder.letterImage.setImageResource(resID);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class PlanViewHolder extends RecyclerView.ViewHolder {
            public TextView txtTitle, txtDate;
            public ImageView letterImage;

            public PlanViewHolder(View itemView) {
                super(itemView);
                getIDS(itemView);

                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        int i=getAdapterPosition();
                        Plan p=list.get(i);
                        listActivityViewModel.deletePlan(p);
                        return false;
                    }
                });
            }

            private void getIDS(View v) {

                letterImage=(ImageView) v.findViewById(R.id.letterImage);
                txtTitle = (TextView) v.findViewById(R.id.txtTitle);
                txtDate = (TextView) v.findViewById(R.id.txtDate);

            }
        }
    }
}