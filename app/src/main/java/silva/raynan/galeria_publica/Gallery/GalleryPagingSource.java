package silva.raynan.galeria_publica.Gallery;

import androidx.annotation.NonNull;
import androidx.paging.ListenableFuturePagingSource;
import androidx.paging.PagingState;

import javax.annotation.Nullable;

public class GalleryPagingSource extends ListenableFuturePagingSource<Integer, ImageData> {
    GalleryRepository galleryRepository;
    Integer initialLoadSize = 0;

    public GalleryPagingSource(GalleryRepository galleryRepository){
        this.galleryRepository = galleryRepository;
    }

    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, ImageData> pagingState){
        return null;
    }


}
