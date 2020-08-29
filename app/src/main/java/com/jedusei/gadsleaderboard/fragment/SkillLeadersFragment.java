package com.jedusei.gadsleaderboard.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jedusei.gadsleaderboard.R;
import com.jedusei.gadsleaderboard.adapter.SkillLeadersRvAdapter;
import com.jedusei.gadsleaderboard.model.SkillLeader;
import com.jedusei.gadsleaderboard.viewmodel.SkillLeadersViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SkillLeadersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SkillLeadersFragment extends Fragment {

    private SkillLeadersViewModel viewModel;
    
    public SkillLeadersFragment() {
        // Required empty public constructor
    }

    public static SkillLeadersFragment newInstance() {
        SkillLeadersFragment fragment = new SkillLeadersFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(SkillLeadersViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_skill_leaders, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final SkillLeadersRvAdapter adapter = new SkillLeadersRvAdapter(getContext());
        recyclerView.setAdapter(adapter);
        viewModel.getSkillLeaders()
                .observe(this, new Observer<List<SkillLeader>>() {
                    @Override
                    public void onChanged(List<SkillLeader> skillLeaders) {
                        adapter.setItems(skillLeaders);
                    }
                });

        return view;
    }
}