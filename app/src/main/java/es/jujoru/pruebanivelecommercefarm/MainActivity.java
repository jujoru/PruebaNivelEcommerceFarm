package es.jujoru.pruebanivelecommercefarm;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import es.jujoru.pruebanivelecommercefarm.Fragments.AllFragment;
import es.jujoru.pruebanivelecommercefarm.Fragments.FilterFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, ServiceMaintenance.class);
        startService(intent);
    }

    public void clickShowAll(View view){
        AllFragment allFragment=new AllFragment();
        showFragment(allFragment);
    }

    public void clickShowFilter(View view){
        FilterFragment filterFragment=new FilterFragment();
        showFragment(filterFragment);
    }

    private void showFragment(Fragment fragment){
        FragmentManager fragmentManager =getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_main,fragment);
        fragmentTransaction.commit();
    }
}
