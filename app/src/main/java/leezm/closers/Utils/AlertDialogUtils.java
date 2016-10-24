package leezm.closers.Utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by Administrator on 2016-10-24.
 */

public class AlertDialogUtils {
    public interface OnClickYesListener {
        abstract void onClickYes();
    }

    public interface OnClickNoListener {
        abstract void onClickNo();
    }

    public static void showDialog(Context context, String Message,
                                  final OnClickYesListener listenerYes,
                                  final OnClickNoListener listenerNo) {
        new AlertDialog.Builder(context).setMessage(Message)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        if (listenerYes != null) {
                            listenerYes.onClickYes();
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        if (listenerNo != null) {
                            listenerNo.onClickNo();
                        }
                    }
                }).show();
    }

    public static void showDialog(Context context, String Title,String Message,
                                  final OnClickYesListener listenerYes,
                                  final OnClickNoListener listenerNo) {
        new AlertDialog.Builder(context).setTitle(Title).setMessage(Message)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        if (listenerYes != null) {
                            listenerYes.onClickYes();
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        if (listenerNo != null) {
                            listenerNo.onClickNo();
                        }
                    }
                }).show();
    }

    public static void showDialog(Context context, String Title,String Message,String Yes,String No,
                                  final OnClickYesListener listenerYes,
                                  final OnClickNoListener listenerNo) {
        new AlertDialog.Builder(context).setTitle(Title).setMessage(Message)
                .setPositiveButton(Yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        if (listenerYes != null) {
                            listenerYes.onClickYes();
                        }
                    }
                })
                .setNegativeButton(No, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        if (listenerNo != null) {
                            listenerNo.onClickNo();
                        }
                    }
                }).show();
    }
}
