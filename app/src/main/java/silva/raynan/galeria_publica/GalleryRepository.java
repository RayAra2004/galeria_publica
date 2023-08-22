package silva.raynan.galeria_publica;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class GalleryRepository {
    Context context;

    public GalleryRepository(Context context) {
        this.context = context;
    }

    public List<ImageData> loadImageData(Integer limit, Integer offset) throws FileNotFoundException{
        List<ImageData> imageDataList = new ArrayList<>();
        int w = (int) context.getResources().getDimension(R.dimen.im_width);
        int h = (int) context.getResources().getDimension(R.dimen.im_height);

        String[] projection = new String[]{
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DATE_ADDED,
                MediaStore.Images.Media.SIZE
        };
        String selection = null;
        String selectionArgs[] = null;
        String sort = MediaStore.Images.Media.DATE_ADDED;
        Cursor cursor = null;
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.Q){
            Bundle queryArgs = new Bundle();
        }
    }
}
