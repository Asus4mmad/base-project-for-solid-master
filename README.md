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







## 🎯 چه تغییراتی اعمال شد؟

### قبل از Refactoring
```java
// همه چیز توی یک کلاس! 😱
class ReservationService {
    - رزرو می‌کنه
    - پرداخت انجام میده
    - ایمیل و SMS می‌فرسته
    - صورت‌حساب می‌سازه
    - تخفیف حساب می‌کنه
}
```

### بعد از Refactoring
```java
// هر کلاس یک مسئولیت واضح داره ✨
ReservationService  → فقط رزرو
PaymentProcessor    → فقط پرداخت
EmailSender         → فقط ایمیل
SmsSender           → فقط اس‌ام‌اس
InvoiceService      → فقط صورت‌حساب
DiscountService     → فقط محاسبه تخفیف
```

---

## 🧩 ساختار جدید پروژه

### 📂 Interfaces (انتزاع‌ها)
- **`PaymentProvider`** - قرارداد مشترک برای تمام روش‌های پرداخت
- **`EmailNotifier`** - فقط برای ارسال ایمیل
- **`SmsNotifier`** - فقط برای ارسال اس‌ام‌اس

### 🔧 Implementations (پیاده‌سازی‌ها)
- `CardPaymentProvider` - پرداخت با کارت
- `CashPaymentProvider` - پرداخت نقدی
- `PayPalPaymentProvider` - پرداخت با پی‌پال
- `OnSitePaymentProvider` - پرداخت در محل
- `EmailSender` - ارسال ایمیل 
- `SmsSender` - ارسال اس‌ام‌اس 

### 🏭 Factories & Services
- `PaymentProviderFactory` - می‌گه کدوم روش پرداخت استفاده بشه
- `PaymentProcessor` - فرآیند پرداخت رو اجرا می‌کنه
- `NotificationService` - مدیریت اعلان‌ها (ایمیل/اس‌ام‌اس)
- `DiscountService` - محاسبه تخفیف
- `InvoiceService` - صدور صورت‌حساب

---

## 🎨 اصول SOLID در عمل

### 1️⃣ **S**ingle Responsibility Principle (SRP)
> هر کلاس فقط یک دلیل برای تغییر داشته باشه

**قبل:**
```java
ReservationService {
    makeReservation()
    processPayment()      // ❌ مسئولیت اضافه
    sendEmail()           // ❌ مسئولیت اضافه
    calculateDiscount()   // ❌ مسئولیت اضافه
}
```

**بعد:**
```java
ReservationService { makeReservation() }     // ✅ فقط رزرو
PaymentProcessor { processPayment() }        // ✅ فقط پرداخت
EmailSender { sendEmail() }                  // ✅ فقط ایمیل
DiscountService { calculateDiscount() }      // ✅ فقط تخفیف
```

---

### 2️⃣ **O**pen/Closed Principle (OCP)
> باز برای توسعه، بسته برای تغییر

**قبل:**
```java
// برای اضافه کردن روش پرداخت جدید باید کل کد رو تغییر بدیم 😞
if (method.equals("card")) { ... }
else if (method.equals("cash")) { ... }
else if (method.equals("paypal")) { ... }
```

**بعد:**
```java
// فقط یه کلاس جدید اضافه می‌کنیم، هیچ کد قبلی تغییر نمی‌کنه! 🎉
class BitcoinPaymentProvider implements PaymentProvider {
    public boolean processPayment(double amount) {
        // منطق پرداخت بیت‌کوین
    }
}
```

---

### 3️⃣ **L**iskov Substitution Principle (LSP)
> باید بتونیم والد رو با فرزندش جایگزین کنیم

```java
PaymentProvider provider = new CardPaymentProvider();
// یا
PaymentProvider provider = new PayPalPaymentProvider();

// هر دو یکسان کار می‌کنن، رفتار ثابته ✅
provider.processPayment(100.0);
```

---

### 4️⃣ **I**nterface Segregation Principle (ISP)
> اینترفیس‌های کوچک و تخصصی بهتره از یه اینترفیس بزرگ

**قبل:**
```java
interface MessageSender {
    void sendEmail();    // ❌ SmsSender مجبوره اینو پیاده کنه!
    void sendSms();      // ❌ EmailSender مجبوره اینو پیاده کنه!
}
```

**بعد:**
```java
interface EmailNotifier {
    void sendEmail();    // ✅ فقط برای ایمیل
}

interface SmsNotifier {
    void sendSms();      // ✅ فقط برای اس‌ام‌اس
}
```

---

### 5️⃣ **D**ependency Inversion Principle (DIP)
> وابستگی به انتزاع، نه جزئیات

**قبل:**
```java
class ReservationService {
    private EmailSender emailSender = new EmailSender();  // ❌ وابستگی مستقیم
}
```

**بعد:**
```java
class ReservationService {
    private final EmailNotifier emailNotifier;  // ✅ وابستگی به interface
    
    public ReservationService(EmailNotifier emailNotifier) {
        this.emailNotifier = emailNotifier;
    }
}
```

---

### ➕ **Principle of Least Knowledge** (PLK / Law of Demeter)
> با دوستات حرف بزن، نه با دوستای دوستات!

**قبل:**
```java
String name = reservation.getCustomer().getName();  // ❌ زنجیره دسترسی
```

**بعد:**
```java
String name = reservation.getCustomerName();  // ✅ helper method
```

---

## 🚀 نحوه استفاده

```java
// 1. ساخت وابستگی‌ها
EmailNotifier emailNotifier = new EmailSender();
SmsNotifier smsNotifier = new SmsSender();
NotificationService notificationService = new NotificationService(emailNotifier, smsNotifier);
DiscountService discountService = new DiscountService();
InvoiceService invoiceService = new InvoiceService();

// 2. ساخت سرویس رزرو با Dependency Injection
ReservationService reservationService = new ReservationService(
    notificationService,
    discountService,
    invoiceService
);

// 3. استفاده
Customer customer = new Customer("علی", "ali@example.com", "09123456789");
Room room = new Room(101, "VIP", 500.0);
Reservation reservation = new Reservation(customer, room, 3);

reservationService.makeReservation(reservation, "card");
```

---


<div dir="rtl" align="right">

## نتیجه‌گیری (اگر از ابتدا SOLID رعایت می‌شد)

در طراحی فعلی (طبق **متن اول**) برای اضافه‌کردن دو قابلیت «پرداخت در محل (ONSITE)» و «ارسال پیامک (SMS)»، مجبور شدیم چند کلاس موجود را تغییر دهیم:

- **پرداخت ONSITE:** تغییر در <code>PaymentProcessor</code> + <code>ReservationService</code> + <code>Main</code> ⟵ یعنی <b>۳ کلاس</b>
- **ارسال SMS:** تغییر در <code>ReservationService</code> + <code>MessageSender</code> + <code>EmailSender</code> + <code>Main</code> ⟵ یعنی <b>۴ کلاس</b>

بنابراین در مجموع <b>۷ تغییر روی کلاس‌های موجود</b> انجام شده است.

اما اگر از ابتدا اصول SOLID رعایت می‌شد (طبق ساختار پیشنهادی در **متن دوم**)، برای افزودن این دو قابلیت:
- نیازی به تغییر در <code>ReservationService</code> و <code>PaymentProcessor</code> و همچنین تحمیل متدهای نامرتبط به <code>EmailSender</code> نبود.
- فقط کافی بود <b>دو کلاس جدید</b> اضافه شود:
  - <code>OnSitePaymentProvider implements PaymentProvider</code>
  - <code>SmsSender implements SmsNotifier</code>
- و در نهایت فقط <b>یک تنظیم/تزریق ساده</b> در <code>Main</code> (Composition Root) انجام می‌شد.



</div>
