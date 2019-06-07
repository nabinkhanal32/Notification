package app;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import com.e.servicesandwearable.R;

public class BroadcastReceiverExample extends BroadcastReceiver {
    private NotificationManagerCompat notificationManagerCompat;
    Context context;

    public BroadcastReceiverExample(Context context) {
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean noConnectivity;

        notificationManagerCompat = NotificationManagerCompat.from(context);
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            noConnectivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY,false);

            if (noConnectivity){
                Toast.makeText(context, "Disconnect", Toast.LENGTH_SHORT).show();
                DisplayNotification1(); 
            }else {
                Toast.makeText(context, "Connect", Toast.LENGTH_SHORT).show();
                DisplayNotification2();
            }
        }
    }

    private void DisplayNotification1() {
        Notification builder = new NotificationCompat.Builder(context,CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.ic_network_check_black_24dp)
                .setContentTitle("No Connection")
                .setContentText("No connectivity, please connect")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE).build();
        notificationManagerCompat.notify(1,builder);

    }

    private void DisplayNotification2() {
        Notification builder = new NotificationCompat.Builder(context,CreateChannel.CHANNEL_2)
                .setSmallIcon(R.drawable.ic_wifi_black_24dp)
                .setContentTitle("Connection")
                .setContentText("you have been connected to a network ")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE).build();
        notificationManagerCompat.notify(2,builder);

    }
}
