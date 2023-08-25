package silva.raynan.galeria_publica.Model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import silva.raynan.galeria_publica.R;

public class MainViewModel extends AndroidViewModel {
    int navigationOpSelected = R.id.gridViewOp;

    public MainViewModel(@NonNull Application application){
        super(application);
    }

    public int getNavigationOpSelected(){
        return navigationOpSelected;
    }

    public void setNavigationOpSelected(int navigationopSelected) {
        this.navigationOpSelected = navigationopSelected;
    }
}
