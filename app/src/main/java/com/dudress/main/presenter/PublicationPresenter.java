package com.dudress.main.presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.dudress.main.DressApplication;
import com.dudress.main.model.PublicationModel;
import com.dudress.main.ui.activities.PublicationActivity;
import com.dudress.main.view.PublicationView;

/**
 * Created by zhou on 16-4-14.
 */
public class PublicationPresenter {

    private int MAX_PIC_SELECTED = 5 ;

    private PublicationView publicationView ;
    private PublicationModel model;
    public PublicationPresenter(PublicationView publicationView){
        this.publicationView = publicationView;
        model = new PublicationModel();
    }

    public void addNewPic(Context context,AddPicStyle addPicStyle){
        int picSize = publicationView.getSelectedPicSize();
        if (picSize < MAX_PIC_SELECTED){
            model.showAddPicDialog(context,addPicStyle);
        }
    }



    public interface AddPicStyle{
        public void takeCamera();
        public void choosePic();
    }

}
