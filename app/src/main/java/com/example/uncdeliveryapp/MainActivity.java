package com.example.uncdeliveryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // criando e instanciando um novo objeto da classe ViewPager
        // e vinculando este objeto ao componente do layout
        ViewPager viewPager = findViewById(R.id.viewPager);

        AuthenticationPageAdapter pageAdapter = new AuthenticationPageAdapter(getSupportFragmentManager());
        pageAdapter.addFragment(new fragment_login());
        pageAdapter.addFragment(new fragment_register());
        viewPager.setAdapter(pageAdapter);
    }

    class AuthenticationPageAdapter extends FragmentPagerAdapter{
        /**
         * criando e instanciando um novo objeto da classe ArrayList
         * para receber os dois fragmentos (login e registro)
         */
        private ArrayList<Fragment> fragmentList = new ArrayList<>();

        // criando um construtor para a classe
        public AuthenticationPageAdapter (FragmentManager fm) {
            super(fm);
        }

        @Override
        // recuperando os fragmentos da lista de fragmentos
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        // obtendo o total de fragmentos da lista de fragmentos
        public int getCount() {
            return fragmentList.size();
        }

        // inserindo os fragmentos na lista de fragmentos
        void addFragment(Fragment fragment) {
            fragmentList.add(fragment);
        }
    }

}