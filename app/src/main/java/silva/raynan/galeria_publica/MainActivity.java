package silva.raynan.galeria_publica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest; //TODO: para resolver os problemas de permissão do Manifest

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import silva.raynan.galeria_publica.Fragment.GridViewFragment;
import silva.raynan.galeria_publica.Fragment.ListViewFragment;
import silva.raynan.galeria_publica.Model.MainViewModel;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    static int RESULT_REQUEST_PERMISSION = 2;


    void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragContainer, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private boolean hasPermission(String permission){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            return ActivityCompat.checkSelfPermission(MainActivity.this, permission) == PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }

    private void checkForPermissions(List<String> permissions){
        List<String> permissionsNotGranted = new ArrayList<>();
        for(String permission : permissions){
            if(!hasPermission(permission)){
                permissionsNotGranted.add(permission);
            }
        }
        if(permissionsNotGranted.size() > 0){
            //TODO: Ir em projetos anteriores para pegar o código!!!
            requestPermissions(permissionsNotGranted.toArray(new String[permissionsNotGranted.size()]), RESULT_REQUEST_PERMISSION);
        }else{
            MainViewModel vm = new ViewModelProvider(this).get(MainViewModel.class);
            int navigationOpSelelected = vm.getNavigationOpSelected();
            bottomNavigationView.setSelectedItemId(navigationOpSelelected);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[]  permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //TODO: Ir em projetos anteriores para pegar o código!!!
        final List<String> permissionsRejected = new ArrayList<>();
        if(requestCode == RESULT_REQUEST_PERMISSION){
            for(String permission : permissions)   {
                if(!hasPermission(permission)){
                    permissionsRejected.add(permission);
                }
            }
        }

        if(permissionsRejected.size() > 0){
            //TODO: Ir em projetos anteriores para pegar o código!!!
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(shouldShowRequestPermissionRationale(permissionsRejected.get(0))){
                    new AlertDialog.Builder(MainActivity.this)
                            .setMessage("Para usar essa app é preciso conceder essas permissões")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    requestPermissions(permissionsRejected.toArray(new String[permissionsRejected.size()]), RESULT_REQUEST_PERMISSION);
                                }
                            }).create().show();
                }
            }
        }else{
            MainViewModel vm = new ViewModelProvider(this).get(MainViewModel.class);
            int navigationOpSelected = vm.getNavigationOpSelected();
            bottomNavigationView.setSelectedItemId(navigationOpSelected);
        }
    }

    protected void onResume(){
        super.onResume();
        List<String> permissions = new ArrayList<>();
        permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        checkForPermissions(permissions);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MainViewModel vm = new ViewModelProvider(this).get(MainViewModel.class);

        bottomNavigationView = findViewById((R.id.btNav));
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                vm.setNavigationOpSelected(item.getItemId());
                int gridViewOp2 = R.id.gridViewOp2;
                int listViewOp2 = R.id.listViewOp2;
                System.out.println(item.getItemId());
                if(R.id.gridViewOp2 == item.getItemId()){
                    GridViewFragment gridViewFragment = GridViewFragment.newInstance();
                    setFragment(gridViewFragment);
                }else if (R.id.listViewOp2 == item.getItemId()){
                    ListViewFragment listViewFragment = ListViewFragment.newInstance();
                    setFragment(listViewFragment);
                }
                /*switch (item.getItemId()){
                    case gridViewOp2:
                        GridViewFragment gridViewFragment = GridViewFragment.newInstance();
                        setFragment(gridViewFragment);
                        break;
                    case listViewOp2:
                        ListViewFragment listViewFragment = ListViewFragment.newInstance();
                        setFragment(listViewFragment);
                        break;
                }*/

                return false;
            }
        });
    }
}