package com.zccicy.common.util;

import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import com.zccicy.R;
import com.zccicy.activity.AvListActivity;
import com.zccicy.model.AvList;

public class DialogUtil {

	public static Dialog createConfirmDialog(Context context, int titleRes,
			int contentRes, DialogConfirmListener listener,Drawable drawable) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context)
				.setTitle(titleRes)
				.setPositiveButton(R.string.dialog_confirm_positive,
						listener.onViewDialogOK())
				.setNegativeButton(R.string.dialog_confirm_negative,
						listener.onViewDialogCancel()).setMessage(contentRes)
				.setCancelable(true)
				.setOnCancelListener(listener.onCancelDialog());
		if (drawable!=null)
			builder.setIcon(drawable);
		return builder.create();

	}

	public static Dialog createListOrderMenu(Context context,
			android.content.DialogInterface.OnClickListener listOrderListener,
			String[] listItems) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context)
				.setTitle(R.string.dialog_title_list_order).setItems(
						listItems, listOrderListener);
		return builder.create();
	}

	public static Dialog createHomeIndexDialog(final Context context,
			final AvList avList) {

		final List<AvList> list = CommonUtil.getChildCategory(context,
				avList.getTid());
		String typeName=avList.getType_name();
		avList.setType_name("软妹都嫁我");
		list.add(avList);

		String[] params = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			params[i] = list.get(i).getType_name();
		}
		avList.setType_name(typeName);
		AlertDialog.Builder builder = new AlertDialog.Builder(context)
				.setTitle(avList.getType_name()).setItems(params,
						new OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								Intent intent = new Intent(context,
										AvListActivity.class);
								intent.putExtra("tid", list.get(which).getTid());
								context.startActivity(intent);
							}
						});
		return builder.create();
	}

	public interface DialogConfirmListener {
		OnClickListener onViewDialogOK();

		OnClickListener onViewDialogCancel();

		OnCancelListener onCancelDialog();
	}

}
