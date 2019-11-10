package com.example.my_spender.ui.consejos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.my_spender.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class ConsejosFragment extends Fragment {

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_consejos, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tabLayout = (TabLayout) view.findViewById(R.id.tabconsejos);

        appBarLayout = (AppBarLayout) view.findViewById(R.id.appbconsejos);
        viewPager = (ViewPager) view.findViewById(R.id.view_pagercons);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        //Adding Fragments
        adapter.AddFragment(new ConsejosCahorroFragment(), "Cachorros");
        adapter.AddFragment(new ConsejosAdultoFragment(), "Adulto");
        //adapter Setup
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.dogw);
        tabLayout.getTabAt(1).setIcon(R.drawable.dogw1);
    }
}