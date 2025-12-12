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
      <td><code>PaymentProcessor</code></td>
      <td>اضافه شدن متد <code>payInPerson(double amount)</code> و چاپ <code>Paid in person: ...</code></td>
      <td><code>ReservationService.java</code></td>
      <td>اضافه شدن شاخه <code>case SMS</code> و ساخت <code>SmsSender</code> و فراخوانی <code>sendSms(res.customer.mobile, "Your reservation confirmed!")</code> و چاپ پیام موفقیت.</td>
    </tr>
    <tr>
      <td>2</td>
      <td><code>ReservationService.java</code></td>
      <td>اضافه شدن <code>case ONSITE</code> و فراخوانی <code>paymentProcessor.payInPerson(res.totalPrice());</code> و <code>break;</code></td>
      <td><code>MessageSender.java</code></td>
      <td>اضافه شدن  متد: <code>public void sendSms(String phoneNumber, String message);</code></td>
    </tr>
    <tr>
      <td>3</td>
      <td><code>Main</code></td>
      <td><code>PaymentMethods.</code> شامل <code>PAYPAL, CARD, CASH, ONSITE</code> است و حالت مدنظر <code>ONSITE</code> می‌باشد. نمونه: <code>service.makeReservation(res, PaymentMethods.PAYPAL, Notifier.SMS);</code></td>
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



| اصل | وضعیت | کلاس | علت برقراری / نقض |
|---|---|---|---|
| **SRP** | مورد برقراری  | `Customer`, `Room`, `Reservation` | هر کلاس فقط داده‌ها و رفتارهای مرتبط با خودش را دارد و معمولاً تنها یک دلیل برای تغییر دارد (کارهای ساده مثل محاسبه‌ی قیمت). |
| **SRP** | مورد نقض  | `ReservationService` | چند مسئولیت را هم‌زمان انجام می‌دهد: اعمال تخفیف، مدیریت پرداخت، چاپ/نمایش خلاصه رزرو، و ارسال پیام تأیید. |
| **OCP** | مورد برقراری  | `PaymentMethods`, `Notifier` | استفاده از `enum` به‌جای رشته‌های خام، کد را خواناتر می‌کند و احتمال خطاهای تایپی را کم می‌کند. |
| **OCP** | مورد نقض  | `ReservationService`, `PaymentProcessor`, `Main` | برای اضافه‌کردن روش پرداخت جدید باید چند بخش مختلف کد (enum و switch و منطق برنامه) تغییر کند؛ یعنی سیستم برای توسعه «بسته» نیست. |
| **LSP** | مورد برقراری (ضعیف)  | `EmailSender` (پیاده‌سازی `MessageSender`) | در وضعیت فعلی، هرجا `MessageSender` بخواهیم می‌توانیم `EmailSender` بگذاریم و رفتار کلی سیستم بهم نمی‌ریزد. |
| **LSP** | مورد نقض ۱  | اینترفیس `MessageSender` | اینترفیس عمومی به نظر می‌رسد ولی فقط متد `sendEmail` دارد؛ اضافه‌شدن SMS باعث می‌شود پیاده‌سازی‌ها متدهای غیرمرتبط را هم تحمل کنند یا ساختار عوض شود. |
| **LSP** | مورد نقض ۲  | `ReservationService` | سرویس عملاً به ارسال ایمیل گره خورده (وابستگی مستقیم/ساختن concrete)، بنابراین جایگزینی پیام‌رسان دیگر بدون تغییر سرویس سخت می‌شود. |
| **ISP** | مورد برقراری  | `Customer`, `Room`, `Reservation` | کلاس‌های مدل مجبور به پیاده‌سازی اینترفیس‌های سنگین و بی‌ربط نیستند و فقط همان چیزی را دارند که لازم دارند. |
| **ISP** | مورد نقض  | `MessageSender` | اینترفیس فقط برای نیاز ایمیل طراحی شده؛ اگر قابلیت‌های جدید اضافه شود یا اینترفیس باد می‌کند یا پیاده‌سازی‌ها متدهای غیرمرتبط را دارند. |
| **DIP** | مورد برقراری (نسبی)  | `PaymentMethods`, `Notifier` | استفاده از enum وابستگی به مقادیر متنی پراکنده را کمتر می‌کند (ولی به‌تنهایی DIP کامل نیست). |
| **DIP** | مورد نقض  | `ReservationService` | به‌جای وابستگی به abstraction، مستقیم concrete می‌سازد (مثل `new PaymentProcessor()` و `new EmailSender()`) و ماژول سطح بالا به جزئیات سطح پایین وابسته می‌شود. |
| **PLK** | مورد برقراری  | `Reservation` | عمدتاً با داده‌های خودش کار می‌کند و خیلی وارد جزئیات داخلی سایر کلاس‌ها نمی‌شود. |
| **PLK** | مورد نقض  | `ReservationService` | دسترسی‌های زنجیره‌ای زیاد دارد (مثل `res.customer.name` و `res.room.number`) و بیش از حد به ساختار داخلی مدل‌ها وابسته است. |
| **CRP** | مورد برقراری  | پکیج مدل‌ها (`Customer`, `Room`, `LuxuryRoom`, `Reservation`) | این کلاس‌ها معمولاً با هم استفاده می‌شوند و کنار هم بودنشان منطقی است. |
| **CRP** | مورد نقض  | `ReservationService` + کلاس‌های پرداخت/اعلان | وابستگی‌ها فشرده است؛ تغییر/استفاده از یک بخش معمولاً بقیه بخش‌ها را هم درگیر می‌کند و reuse سخت‌تر می‌شود. |
