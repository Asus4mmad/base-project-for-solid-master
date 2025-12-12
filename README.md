# base-project-for-solid-master
پروژه درس شی گرایی


<table dir="rtl">
  <thead>
    <tr>
      <th rowspan="2">ردیف</th>
      <th colspan="2">تغییرات مربوط به افزودن روش جدید پرداخت</th>
      <th colspan="2">تغییرات مربوط به افزودن روش جدید ارسال پیام</th>
    </tr>
    <tr>
      <th>کلاس تغییر یافته</th>
      <th>توضیح کوتاه در مورد تغییر</th>
      <th>کلاس تغییر یافته</th>
      <th>توضیح کوتاه در مورد تغییر</th>
    </tr>
  </thead>

  <tbody>
    <tr>
      <td>1</td>
      <td>—</td>
      <td>—</td>
      <td><code>ReservationService.java</code></td>
      <td>اضافه شدن شاخه <code>case SMS</code> و ساخت <code>SmsSender</code> و فراخوانی <code>sendSms(res.customer.mobile, "Your reservation confirmed!")</code> و چاپ پیام موفقیت.</td>
    </tr>
    <tr>
      <td>2</td>
      <td>—</td>
      <td>—</td>
      <td><code>MessageSender.java</code></td>
      <td>اضافه شدن  متد: <code>public void sendSms(String phoneNumber, String message);</code></td>
    </tr>
    <tr>
      <td>3</td>
      <td>—</td>
      <td>—</td>
      <td><code>EmailSender.java</code></td>
      <td>اضافه شدن <code>@Override</code> برای <code>sendSms(...)</code> (بدون پیاده‌سازی واقعی، چون SMS برای EmailSender در دسترس نیست).</td>
    </tr>
    <tr>
      <td>4</td>
      <td>—</td>
      <td>—</td>
      <td><code>Main</code></td>
      <td>نمونه استفاده: <code>service.makeReservation(res, PaymentMethods.PAYPAL, Notifier.EMAIL);</code> (برای تست SMS می‌توانید <code>Notifier.SMS</code> بگذارید).</td>
    </tr>
  </tbody>
</table>


