package it.beppi.blog;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Beppi on 11/11/2016.
 */

public class BLog {
    private static Context ctx=null;                 // necessario impostare se si usano toast
    private static boolean DEBUG_ON = true;   // abilita / disabilita debug
    private static boolean TOAST_ON = true;   // abilita reflect del log via toast
    private static boolean POPUP_ON = true;   // abilita / disabilita dialog popup
    private static boolean SHOW_PREV_COMMAND = false;  // abilita / disabilita il mostrare comandi precedenti
    private static boolean LOG_ON = true;     // abilita disabilita logcat
    private static String TAG = "beppiLog";      // tag per filtrare il log


    private static String methodName = "";
    private static int methodCount = 1;
    private static void priv_show(String message, int depth, boolean log, boolean toast, boolean popup) {
        if (!DEBUG_ON) return;
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        String s2 = "";
        if (SHOW_PREV_COMMAND)
            s2 = "[ " + ste[4+depth].getMethodName() + "() ]==> ";
        String method = ste[3+depth].getMethodName();
        if (method.equals(methodName))
            methodCount++;
        else {
            methodName = method;
            methodCount = 1;
        }
        method += "() #" + Integer.toString(methodCount);
        method = s2 + method;

        String complete_message = "";
        if (!message.equals(""))
            complete_message = method + ": " + message;

        if (log)
            Log.d(TAG, complete_message);
        if (toast)
            show_toast(complete_message);
        if (popup)
            show_popup(method, message);

    }
    private static void priv_show(String message, int depth) {
        priv_show(message, depth+1, LOG_ON, TOAST_ON, POPUP_ON);
    }
    private static void priv_show(String title, String message, int depth) {
        priv_show(title + ": " + message, depth+1);
    }
    private static void show_toast(String complete_message) {
        if (ctx != null)
            Toast.makeText(ctx, complete_message, Toast.LENGTH_SHORT).show();
        else
            Log.d(TAG, "!!! PLEASE SET Context TO USE TOASTS !!!");
    }
    private static void show_popup(String method, String message) {
        try {
            new AlertDialog.Builder(ctx)
                    .setTitle(TAG + " - " + method)
                    .setMessage(message)
                    .setNeutralButton("OK", null)
                    .show();
        } catch (Exception e) {
            Log.d(TAG, "!!! PLEASE SET Context (this) TO USE DIALOGS !!!");
        }
    }

    /**
     * Initialization (not needed for simple log display in logcat)
     * @param context              Context
     * @param toast_on             Should the log be also displayed in a toast?
     * @param popup_on             Should the log be also displayed in a popup dialog?
     * @param show_prev_method     Should the method include also previous method in the stack trace?
     */
    public static void init(Context context, boolean toast_on, boolean popup_on, boolean show_prev_method) {
        ctx = context;
        TOAST_ON = toast_on;
        POPUP_ON = popup_on;
        SHOW_PREV_COMMAND = show_prev_method;
        DEBUG_ON = true;
    }
    public static void init(Context context) {
        init (context, false, true, false);
    }
    public static void init(Context context, String tag) { init (context, false, true, false); TAG = tag; }
    public static void init(Context context, String tag, boolean toast_on, boolean popup_on, boolean show_prev_method) { init (context, toast_on, popup_on, show_prev_method); TAG = tag; }
    public static void init(String tag) { TAG = tag; }
    public static void setDebug(boolean on) {
        DEBUG_ON = on;
    }

    /**
     * Show a message in every possible way
     * @param message
     */
    public static void d(String message) {
        priv_show(message, 1);
    }
    public static void d() {
        priv_show("", 1);
    }
    public static void d(int... n) {
        String s = "";
        for (int w=0; w<n.length; w++) {
            if (w>0) s += ", ";
            s += Integer.toString(n[w]);
        }
        priv_show(s, 1);
    }
    public static void d(String... messages) {
        String s = "";
        for (int w=0; w<messages.length; w++) {
            if (w>0) s += ", ";
            s += messages[w];
        }
        priv_show(s, 1);
    }
    public static void d(float... f) {
        String s = "";
        for (int w=0; w<f.length; w++) {
            if (w>0) s += ", ";
            s += Float.toString(f[w]);
        }
        priv_show(s, 1);
    }
    public static void d(Boolean... b) {
        String s = "";
        for (int w=0; w<b.length; w++) {
            if (w>0) s += ", ";
            s += Boolean.toString(b[w]);
        }
        priv_show(s, 1);
    }
    public static void d(ArrayList<Object> al) {
        String s = "";
        for (int w=0; w<al.size(); w++) {
            if (w>0) s += ", ";
            s += al.get(w).toString();
        }
        priv_show(s, 1);
    }
    public static void d(List<Object> al) {
        String s = "";
        for (int w=0; w<al.size(); w++) {
            if (w>0) s += ", ";
            s += al.get(w).toString();
        }
        priv_show(s, 1);
    }
    public static void d(Object... o) {
        String s = "";
        for (int w=0; w<o.length; w++) {
            if (w>0) s += ", ";
            s += o[w].toString();
        }
        priv_show(s, 1);
    }
    public static void d(String title, int... n) {
        String s = "";
        for (int w=0; w<n.length; w++) {
            if (w>0) s += ", ";
            s += Integer.toString(n[w]);
        }
        priv_show(title, s, 1);
    }
    public static void d(String title, float... f) {
        String s = "";
        for (int w=0; w<f.length; w++) {
            if (w>0) s += ", ";
            s += Float.toString(f[w]);
        }
        priv_show(title, s, 1);
    }
    public static void d(String title, Boolean... b) {
        String s = "";
        for (int w=0; w<b.length; w++) {
            if (w>0) s += ", ";
            s += Boolean.toString(b[w]);
        }
        priv_show(title, s, 1);
    }
    public static void d(String title, ArrayList<Object> al) {
        String s = "";
        for (int w=0; w<al.size(); w++) {
            if (w>0) s += ", ";
            s += al.get(w).toString();
        }
        priv_show(title, s, 1);
    }

    public static void toastOnly(String message) { priv_show(message, 1, false, true, false);}
    public static void toastOnly() { priv_show("", 1, false, true, false); }
    public static void toastOnly(int... n) {
        String s = "";
        for (int w = 0; w < n.length; w++) {
            if (w > 0) s += ", ";
            s += Integer.toString(n[w]);
        }
        priv_show(s, 1, false, true, false);
    }
    public static void toastOnly(float... f) {
        String s = "";
        for (int w=0; w<f.length; w++) {
            if (w>0) s += ", ";
            s += Float.toString(f[w]);
        }
        priv_show(s, 1, false, true, false);
    }
    public static void toastOnly(Boolean... b) {
        String s = "";
        for (int w=0; w<b.length; w++) {
            if (w>0) s += ", ";
            s += Boolean.toString(b[w]);
        }
        priv_show(s, 1, false, true, false);
    }
    public static void toastOnly(ArrayList<Object> al) {
        String s = "";
        for (int w=0; w<al.size(); w++) {
            if (w>0) s += ", ";
            s += al.get(w).toString();
        }
        priv_show(s, 1, false, true, false);
    }
    public static void toastOnly(List<Object> al) {
        String s = "";
        for (int w=0; w<al.size(); w++) {
            if (w>0) s += ", ";
            s += al.get(w).toString();
        }
        priv_show(s, 1, false, true, false);
    }
    public static void toastOnly(Object... o) {
        String s = "";
        for (int w=0; w<o.length; w++) {
            if (w>0) s += ", ";
            s += o[w].toString();
        }
        priv_show(s, 1, false, true, false);
    }
    public static void toastOnly(String title, int... n) {
        String s = "";
        for (int w=0; w<n.length; w++) {
            if (w>0) s += ", ";
            s += Integer.toString(n[w]);
        }
        priv_show(title, s, 1);
    }
    public static void toastOnly(String title, float... f) {
        String s = "";
        for (int w=0; w<f.length; w++) {
            if (w>0) s += ", ";
            s += Float.toString(f[w]);
        }
        priv_show(title, s, 1);
    }
    public static void toastOnly(String title, Boolean... b) {
        String s = "";
        for (int w=0; w<b.length; w++) {
            if (w>0) s += ", ";
            s += Boolean.toString(b[w]);
        }
        priv_show(title, s, 1);
    }
    public static void toastOnly(String title, ArrayList<Object> al) {
        String s = "";
        for (int w=0; w<al.size(); w++) {
            if (w>0) s += ", ";
            s += al.get(w).toString();
        }
        priv_show(title, s, 1);
    }

    public static void popupOnly(String message) { priv_show(message, 1, false, true, false);}
    public static void popupOnly() { priv_show("", 1, false, true, false); }
    public static void popupOnly(int... n) {
        String s = "";
        for (int w = 0; w < n.length; w++) {
            if (w > 0) s += ", ";
            s += Integer.toString(n[w]);
        }
        priv_show(s, 1, false, false, true);
    }
    public static void popupOnly(float... f) {
        String s = "";
        for (int w=0; w<f.length; w++) {
            if (w>0) s += ", ";
            s += Float.toString(f[w]);
        }
        priv_show(s, 1, false, false, true);
    }
    public static void popupOnly(Boolean... b) {
        String s = "";
        for (int w=0; w<b.length; w++) {
            if (w>0) s += ", ";
            s += Boolean.toString(b[w]);
        }
        priv_show(s, 1, false, false, true);
    }
    public static void popupOnly(ArrayList<Object> al) {
        String s = "";
        for (int w=0; w<al.size(); w++) {
            if (w>0) s += ", ";
            s += al.get(w).toString();
        }
        priv_show(s, 1, false, false, true);
    }
    public static void popupOnly(List<Object> al) {
        String s = "";
        for (int w=0; w<al.size(); w++) {
            if (w>0) s += ", ";
            s += al.get(w).toString();
        }
        priv_show(s, 1, false, false, true);
    }
    public static void popupOnly(Object... o) {
        String s = "";
        for (int w=0; w<o.length; w++) {
            if (w>0) s += ", ";
            s += o[w].toString();
        }
        priv_show(s, 1, false, false, true);
    }
    public static void popupOnly(String title, int... n) {
        String s = "";
        for (int w=0; w<n.length; w++) {
            if (w>0) s += ", ";
            s += Integer.toString(n[w]);
        }
        priv_show(title, s, 1);
    }
    public static void popupOnly(String title, float... f) {
        String s = "";
        for (int w=0; w<f.length; w++) {
            if (w>0) s += ", ";
            s += Float.toString(f[w]);
        }
        priv_show(title, s, 1);
    }
    public static void popupOnly(String title, Boolean... b) {
        String s = "";
        for (int w=0; w<b.length; w++) {
            if (w>0) s += ", ";
            s += Boolean.toString(b[w]);
        }
        priv_show(title, s, 1);
    }
    public static void popupOnly(String title, ArrayList<Object> al) {
        String s = "";
        for (int w=0; w<al.size(); w++) {
            if (w>0) s += ", ";
            s += al.get(w).toString();
        }
        priv_show(title, s, 1);
    }
}
