// Copyright 2018 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package homewarehouse.project.homewarehouse_amzn_api;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;

import java.io.IOException;
import java.util.List;


/** Barcode Detector */
public class BarcodeScanningProcessor extends VisionProcessorBase<List<FirebaseVisionBarcode>> {

  private static final String TAG = "BarcodeScanProc";
  CharSequence text = "Hello toast!";
  int duration = Toast.LENGTH_SHORT;
    Button warehouseButton;



  private final FirebaseVisionBarcodeDetector detector;

  public BarcodeScanningProcessor() {
    // Note that if you know which format of barcode your app is dealing with, detection will be
    // faster to specify the supported barcode formats one by one, e.g.
//     new FirebaseVisionBarcodeDetectorOptions.Builder()
//         .setBarcodeFormats(FirebaseVisionBarcode.FORMAT_QR_CODE)
//         .build();
    detector = FirebaseVision.getInstance().getVisionBarcodeDetector();
  }

  @Override
  public void stop() {
    try {
      detector.close();
    } catch (IOException e) {
      Log.e(TAG, "Exception thrown while trying to close Barcode Detector: " + e);
    }
  }

  @Override
  protected Task<List<FirebaseVisionBarcode>> detectInImage(FirebaseVisionImage image) {
    return detector.detectInImage(image);
  }

  @Override
  protected void onSuccess(
      @NonNull List<FirebaseVisionBarcode> barcodes,
      @NonNull FrameMetadata frameMetadata,
      @NonNull GraphicOverlay graphicOverlay) {

    graphicOverlay.clear();

      for (FirebaseVisionBarcode barcode: barcodes) {
          Rect bounds = barcode.getBoundingBox();
          Point[] corners = barcode.getCornerPoints();
          String rawValue = barcode.getRawValue();
          JsonResultsActivity jsonResultsActivity = new JsonResultsActivity();
          jsonResultsActivity.newThread(rawValue);
          //Thread.interrupted();

      }

    /*
    for (int i = 0; i < barcodes.size(); ++i) {
      FirebaseVisionBarcode barcode = barcodes.get(i);
      String rawValue = barcode.getRawValue();
      BarcodeGraphic barcodeGraphic = new BarcodeGraphic(graphicOverlay, barcode);
        System.out.println ("Barcode: " + barcode );
      graphicOverlay.add(barcodeGraphic);
        System.out.println ("Barcode: " + barcode );

    }
    */


  }


    @Override
  protected void onFailure(@NonNull Exception e) {
    Log.e(TAG, "Barcode detection failed " + e);
  }
}
