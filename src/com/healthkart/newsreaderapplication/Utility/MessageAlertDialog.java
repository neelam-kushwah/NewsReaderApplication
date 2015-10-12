package com.healthkart.newsreaderapplication.Utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Looper;

/**
 * @author name:Neelam Kushwah Class name:Message Alert Dialog Class
 *         Description: This class is used to display alert dialog
 **/
public class MessageAlertDialog {

	static AlertDialog.Builder alertDialog;

	public static void showAlertDialog(String title, String message,
			Context context) {
		try {
			if (!((Activity) context).isFinishing()) {
				alertDialog = new AlertDialog.Builder(context);

				// set title

				alertDialog.setMessage(message)
						.setTitle(title)
						.setCancelable(true)
						// set positive button
						.setPositiveButton("Ok",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										dialog.cancel();
									}
								});

				// show dialog
				alertDialog.show();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// method for showing alert dialog
	public static void showErrorAlert(String ErrMsg, Context context) {
		MessageAlertDialog.showAlertDialog("Warning", ErrMsg, context);
	}

}
