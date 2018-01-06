package ru.meizu.changeid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;
import java.io.*;

public class MainActivity extends Activity {

 public static void sudo(String...strings) {
  try {
   Process su = Runtime.getRuntime().exec("su");
   DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());

   for (String s: strings) {
    outputStream.writeBytes(s + "\n");
    outputStream.flush();
   }

   outputStream.writeBytes("exit\n");
   outputStream.flush();
   try {
    su.waitFor();
   } catch (InterruptedException e) {
    e.printStackTrace();
   }
   outputStream.close();
  } catch (IOException e) {
   e.printStackTrace();
  }
 }

 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);

  TextView phone = (TextView) findViewById(R.id.phone_text);
  phone.setText(android.os.Build.MANUFACTURER + " " + android.os.Build.MODEL);
  Button button = (Button) findViewById(R.id.button_id);
  button.setOnClickListener(new View.OnClickListener() {
   public void onClick(View v) {

    String str = new String();

    if (android.os.Build.MANUFACTURER.equals("Meizu")) {
     str = "Id was changed.";
     switch (android.os.Build.MODEL) {
      case "m2 note":
       sudo("dd if=/dev/block/mmcblk0p3 of=/sdcard/devinfo_backup_$(date +%Y-%m-%d).img");
       sudo("echo -e '5' | dd of=/dev/block/mmcblk0 bs=1 seek=13107717 count=1");
       break;

      case "M5 Note":
       sudo("dd if=/dev/block/mmcblk0p28 of=/sdcard/devinfo_backup_$(date +%Y-%m-%d).img");
       sudo("echo -e '5' | dd of=/dev/block/mmcblk0p28 bs=1 seek=515 count=1");
       break;

      case "M6 Note":
       sudo("dd if=/dev/block/mmcblk0p50 of=/sdcard/devinfo_backup_$(date +%Y-%m-%d).img");
       sudo("echo -e '5' | dd of=/dev/block/mmcblk0p50 bs=1 seek=515 count=1");
       sudo("echo -e '2' | dd of=/dev/block/mmcblk0p50 bs=1 seek=519 count=1");
       break;

      case "PRO 6":
       sudo("dd if=/dev/block/mmcblk0p4 of=/sdcard/devinfo_backup_$(date +%Y-%m-%d).img");
       sudo("echo -e '5' | dd of=/dev/block/mmcblk0 bs=1 seek=568885765 count=1");
       sudo("echo -e '1' | dd of=/dev/block/mmcblk0 bs=1 seek=568885769 count=1");
       break;

      case "PRO 6s":
       sudo("dd if=/dev/block/mmcblk0p4 of=/sdcard/devinfo_backup_$(date +%Y-%m-%d).img");
       sudo("echo -e '5' | dd of=/dev/block/mmcblk0 bs=1 seek=568885765 count=1");
       sudo("echo -e '1' | dd of=/dev/block/mmcblk0 bs=1 seek=568885769 count=1");
       break;

      case "M5s":
       sudo("dd if=/dev/block/mmcblk0p8 of=/sdcard/devinfo_backup_$(date +%Y-%m-%d).img");
       sudo("echo -e '5' | dd of=/dev/block/mmcblk0p18 bs=1 seek=517 count=1");
       break;

      case "M5":
       sudo("dd if=/dev/block/mmcblk0p8 of=/sdcard/devinfo_backup_$(date +%Y-%m-%d).img");
       sudo("echo -e '5' | dd of=/dev/block/mmcblk0p15 bs=1 seek=517 count=1");
       sudo("echo -e '2' | dd of=/dev/block/mmcblk0p15 bs=1 seek=521 count=1");
       break;

      case "M6":
       sudo("dd if=/dev/block/mmcblk0p8 of=/sdcard/devinfo_backup_$(date +%Y-%m-%d).img");
       sudo("echo -e '5' | dd of=/dev/block/mmcblk0p8 bs=1 seek=515 count=1");
       sudo("echo -e '2' | dd of=/dev/block/mmcblk0p8 bs=1 seek=519 count=1");
       break;

      case "MX5":
       sudo("dd if=/dev/block/mmcblk0p3 of=/storage/emulated/0/devinfo_backup_$(date +%Y-%m-%d).img");
       sudo("echo -e '5' | dd of=/dev/block/mmcblk0 bs=1 seek=13107717 count=1");
       break;

      case "MX6":
       sudo("dd if=/dev/block/mmcblk0p16 of=/sdcard/devinfo_backup_$(date +%Y-%m-%d).img");
       sudo("echo -e '5' | dd of=/dev/block/mmcblk0p16 bs=1 seek=517 count=1");
       break;

      case "U10":
       sudo("dd if=/dev/block/mmcblk0p15 of=/sdcard/devinfo_backup_$(date +%Y-%m-%d).img");
       sudo("echo -e '5' | dd of=/dev/block/mmcblk0p15 bs=1 seek=517 count=1");
       break;

      case "U20":
       sudo("dd if=/dev/block/mmcblk0p28 of=/sdcard/devinfo_backup_$(date +%Y-%m-%d).img");
       sudo("echo -e '5' | dd of=/dev/block/mmcblk0p28 bs=1 seek=517 count=1");
       break;

      default:
       str = "Unknown Meizu model. Id wasn't changed.";
       break;
     }
    } else {
     str = "Not Meizu phone. Nothing to change.";
    }

    Toast toast = Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG);
    toast.show();
   }
  });
 }
}
