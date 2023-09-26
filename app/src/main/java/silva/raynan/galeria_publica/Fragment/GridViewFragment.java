package silva.raynan.galeria_publica.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagingData;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import silva.raynan.galeria_publica.Adapter.GridAdapter;
import silva.raynan.galeria_publica.Adapter.ListAdapter;
import silva.raynan.galeria_publica.Image.ImageData;
import silva.raynan.galeria_publica.Image.ImageDataComparator;
import silva.raynan.galeria_publica.Model.MainViewModel;
import silva.raynan.galeria_publica.R;
import silva.raynan.galeria_publica.Util.Util;

public class GridViewFragment extends Fragment {

    private MainViewModel mViewModel;
    private View view;

    public static GridViewFragment newInstance() {return new GridViewFragment();}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_grid_view, container, false);
        return view;
    }

    @Override
    public  void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        GridAdapter gridAdapter = new GridAdapter(new ImageDataComparator());
        LiveData<PagingData<ImageData>> liveData = mViewModel.getPageLv();
        liveData.observe(getViewLifecycleOwner(), new Observer<PagingData<ImageData>>() {
            @Override
            public void onChanged(PagingData<ImageData> objectPagingData) {
                gridAdapter.submitData(getViewLifecycleOwner().getLifecycle(), objectPagingData);
            }
        });
        float w = getResources().getDimension(R.dimen.im_width);
        int numberOfColumns = Util.calculateNoOfColumns(getContext(), w);

        RecyclerView rvGallery = (RecyclerView) view.findViewById(R.id.rvGrid);
        rvGallery.setAdapter(gridAdapter);
        rvGallery.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));
    }
}