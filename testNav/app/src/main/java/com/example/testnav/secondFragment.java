package com.example.testnav;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link secondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class secondFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public secondFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment secondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static secondFragment newInstance(String param1, String param2) {
        secondFragment fragment = new secondFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    private RecyclerView recyclerView;
    private ArrayList<ParentModelClass> parentModelClassArrayList;
    private ArrayList<ChildModelClass> childModelClassArrayList;
    private ArrayList<ChildModelClass> recentlyList;
    private ArrayList<ChildModelClass> favoriteList;
    private ArrayList<ChildModelClass> latestList;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerView = view.findViewById(R.id.rv_parent);
        parentModelClassArrayList=new ArrayList<>();
        childModelClassArrayList=new ArrayList<>();
        recentlyList=new ArrayList<>();
        favoriteList=new ArrayList<>();
        latestList=new ArrayList<>();
        ParentAdapter parentAdapter;

        latestList.add(new ChildModelClass(R.drawable.img1,"Đua Xe"));
        latestList.add(new ChildModelClass(R.drawable.img2,"Bắn Súng"));
        latestList.add(new ChildModelClass(R.drawable.img3,"Đánh Nhau"));
        latestList.add(new ChildModelClass(R.drawable.img4,"Chém Hoa Quả"));
        latestList.add(new ChildModelClass(R.drawable.img6,"Búp bê"));
        latestList.add(new ChildModelClass(R.drawable.img7,"Xây Nhà"));
        parentModelClassArrayList.add(new ParentModelClass("List Game 1",latestList));

        recentlyList.add(new ChildModelClass(R.drawable.img7,"Đua Xe"));
        recentlyList.add(new ChildModelClass(R.drawable.img6,"Bắn Súng"));
        recentlyList.add(new ChildModelClass(R.drawable.img5,"Búp bê"));
        recentlyList.add(new ChildModelClass(R.drawable.img4,"Chém Hoa Quả"));
        recentlyList.add(new ChildModelClass(R.drawable.img3,"Chém Hoa Quả"));
        recentlyList.add(new ChildModelClass(R.drawable.img1,"Xây Nhà"));
        parentModelClassArrayList.add(new ParentModelClass("List Game 2",recentlyList));

        favoriteList.add(new ChildModelClass(R.drawable.img5,"Nhà"));
        favoriteList.add(new ChildModelClass(R.drawable.img3,"Bắn Súng"));
        favoriteList.add(new ChildModelClass(R.drawable.img2,"Bắn Súng"));
        favoriteList.add(new ChildModelClass(R.drawable.img1,"Chém Hoa Quả"));
        favoriteList.add(new ChildModelClass(R.drawable.img7,"Chém Hoa Quả"));
        favoriteList.add(new ChildModelClass(R.drawable.img1,"Chém Hoa Quả"));
        parentModelClassArrayList.add(new ParentModelClass("List Game 3",favoriteList));


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        parentAdapter=new ParentAdapter(parentModelClassArrayList, getContext());
        recyclerView.setAdapter(parentAdapter);
        parentAdapter.notifyDataSetChanged();
    }
}