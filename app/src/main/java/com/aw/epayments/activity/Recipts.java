package com.aw.epayments.activity;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.aw.epayments.Adapters.RecieptAdapter;
import com.aw.epayments.R;
import com.aw.epayments.api.Const;
import com.aw.epayments.models.Response.TransactionDetail;
import com.aw.epayments.models.Response.TransactionStatusResponse;
import com.aw.epayments.utility.FileUtil;
import com.aw.epayments.utility.ScreenShot;
import com.aw.epayments.utility.ScreenshotUtil;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.List;


public class Recipts extends AppCompatActivity {

    TextView date, totalAmount, numberPlate, code, street, parking, penalties, mode, description;
    LinearLayout back, receipt, save_image;
    ImageView imageView;
    TransactionStatusResponse data;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reciept_view);

        description = findViewById(R.id.title);
        date = findViewById(R.id.receipt_date);
        parking = findViewById(R.id.receipt_parking);
        numberPlate = findViewById(R.id.receipt_number_plate);
        code = findViewById(R.id.receipt_unique_code);
        street = findViewById(R.id.receipt_street);
        receipt = findViewById(R.id.receipt);
        totalAmount = findViewById(R.id.receipt_total);
        mode = findViewById(R.id.receipt_payment_mode);
        back = findViewById(R.id.receipt_back);
        save_image = findViewById(R.id.save_image);
        imageView = findViewById(R.id.imageView);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ScreenShot save = new ScreenShot();
        save_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bitmap = ScreenshotUtil.Companion.getInstance().takeScreenshotForScreen(Recipts.this);
                requestPermissionAndSave();
                onClickApp(getPackageName(), bitmap);
                //
            }
        });
        LoadReceipt();

    }

    public void LoadReceipt() {
        /*DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
        DateTime dt = formatter.parseDateTime(string);*/

        data = Const.getInstance().getTransactionStatusResponse();

        LoadTransactions(data.getResponseData().getTransactionDetails());
        date.setText(data.getResponseData().getTransactionTime());
        numberPlate.setText(data.getResponseData().getIdentifier());
        code.setText(data.getResponseData().getReceiptNumber());
        street.setText(data.getResponseData().getStreet());

        mode.setText(data.getResponseData().getPaymentMode());
        description.setText(data.getResponseData().getReceiptFor());
        totalAmount.setText(String.valueOf(data.getResponseData().getTotalAmount()));


    }

    public void LoadTransactions(List<TransactionDetail> transactionDetails) {
        RecyclerView trs = findViewById(R.id.transactions_recipt);
        RecieptAdapter adapter = new RecieptAdapter(this, transactionDetails);
        trs.setItemAnimator(new DefaultItemAnimator());
        trs.setAdapter(adapter);
        // Set layout manager to position the items
        trs.setLayoutManager(new LinearLayoutManager(Recipts.this));
    }

    private void requestPermissionAndSave() {

        Dexter.withActivity(this)
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {

                        if (bitmap != null) {
                            String path = Environment.getExternalStorageDirectory().toString() + "/" + data.getResponseData().getReceiptNumber() + ".png"/*data.getResponseData().getReceiptNumber()*/;
                            FileUtil.Companion.getInstance().storeBitmap(bitmap, path);
                            Toast.makeText(Recipts.this, getString(R.string.toast_message_screenshot_success) + " " + path, Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Recipts.this, getString(R.string.toast_message_screenshot), Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        // check for permanent denial of permission
                        if (response.isPermanentlyDenied()) {
                            Toast.makeText(Recipts.this, getString(R.string.settings_message), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    public void onClickApp(String pack, Bitmap bitmap) {
        PackageManager pm = this.getPackageManager();
        try {
           /* ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(this.getContentResolver(), bitmap, "Title", null);
            Uri imageUri = Uri.parse(path);

            @SuppressWarnings("unused")
            PackageInfo info = pm.getPackageInfo(pack, PackageManager.GET_META_DATA);

            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("image/*");
            waIntent.setPackage(pack);
            waIntent.putExtra(android.content.Intent.EXTRA_STREAM, imageUri);
            waIntent.putExtra(Intent.EXTRA_TEXT, pack);
            this.startActivity(Intent.createChooser(waIntent, "Share with"));*/
            String path = MediaStore.Images.Media.insertImage(this.getContentResolver(), bitmap, "Title", null);
            Uri imageUri = Uri.parse(path);
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "Receipt Number " + data.getResponseData().getReceiptNumber() + "";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            sharingIntent.putExtra(android.content.Intent.EXTRA_STREAM, imageUri);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
        } catch (Exception e) {
            Log.e("Error on sharing", e + " ");
            Toast.makeText(this, "App not Installed", Toast.LENGTH_SHORT).show();
        }
    }
}
