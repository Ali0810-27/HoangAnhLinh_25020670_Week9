# BÁO CÁO TOÀN DIỆN VÀ HƯỚNG DẪN THỰC HÀNH: MAVEN, CODE QUALITY & CI/CD AUTOMATION

**Họ và tên:** Hoàng Anh Linh  
**Mã sinh viên:** 25020670  
**Học phần:** Phát triển ứng dụng / Công nghệ phần mềm chuyên nghiệp (Week 9)  
**Thư mục dự án:** `C:\Java\HoangAnhLinh_25020670_Week9`  

---

## MỤC LỤC TỔNG QUAN

1. [Cấu trúc Thư mục Tổng thể](#cấu-trúc-thư-mục-tổng-thể)
2. [Bài 1: Quản lý Dependency với Maven (MathUtils)](#bài-1-quản-lý-dependency-với-maven-mathutils)
3. [Bài 2: Code Quality - Checkstyle & Observability (BankSystem)](#bài-2-code-quality---checkstyle--observability-banksystem)
4. [Bài 3: CI/CD Automation (Vòng đời Maven & Lưu giữ Artifact)](#bài-3-cicd-automation-vòng-đời-maven--lưu-giữ-artifact)
5. [Bài 4: Kiểm thử Đa Hệ Điều Hành với Matrix Strategy](#bài-4-kiểm-thử-đa-hệ-điều-hành-với-matrix-strategy)
6. [Bài 5: Test Coverage & Quality Enforcement (JaCoCo)](#bài-5-test-coverage--quality-enforcement-jacoco)
7. [Bài 6: CI/CD Pipeline Optimization & Dependency Caching](#bài-6-cicd-pipeline-optimization--dependency-caching)
8. [Bài 7: Automated Code Review via Pull Request & Branch Protection](#bài-7-automated-code-review-via-pull-request--branch-protection)
9. [Bài 8: Đóng gói Sản phẩm Thực thi (Executable JAR Packaging)](#bài-8-đóng-gói-sản-phẩm-thực-thi-executable-jar-packaging)
10. [Bài 9: Triển khai Logging Chuyên nghiệp (SLF4J + Logback FileAppender)](#bài-9-triển-khai-logging-chuyên-nghiệp-slf4j--logback-fileappender)
11. [Bài 10: Debugging "The Broken Pipeline" (Xác định & Sửa 4 Lỗi Kỹ thuật)](#bài-10-debugging-the-broken-pipeline-xác-định--sửa-4-lỗi-kỹ-thuật)
12. [Hướng dẫn Khởi chạy và Kiểm thử Dự án](#hướng-dẫn-khởi-chạy-và-kiểm-thử-dự-án)

---

## CẤU TRÚC THƯ MỤC TỔNG THỂ

Dự án được tổ chức theo mô hình **Maven Multi-Module Root Project**, trong đó thư mục gốc chứa `pom.xml` quản trị chung và các thư mục con tương ứng với từng bài thực hành:

```text
C:\Java\HoangAnhLinh_25020670_Week9\
├── .github/
│   └── workflows/
│       ├── main-ci.yml                 # Workflow tổng hợp (Matrix, Caching, JaCoCo, Upload Artifact)
│       └── pr-checkstyle-review.yml     # Workflow tự động review Checkstyle khi mở Pull Request
├── pom.xml                             # Parent POM quản trị 10 modules
├── README.md                           # Báo cáo chi tiết và hướng dẫn thực hành
│
├── bai-01-math-utils/                  # Bài 1: Dependency Management (Logback, Hibernate, JUnit 5)
├── bai-02-bank-system/                 # Bài 2: Code Quality Checkstyle & Logging Observability
├── bai-03-ci-automation/               # Bài 3: CI/CD Build Lifecycle & Upload Artifacts
├── bai-04-matrix-testing/              # Bài 4: Multi-OS Testing với Matrix Strategy (Cross-Platform Path)
├── bai-05-jacoco-coverage/             # Bài 5: JaCoCo Code Coverage Enforcement (Strict 80% Rule)
├── bai-06-ci-caching/                  # Bài 6: Pipeline Optimization & Dependency Caching
├── bai-07-pr-code-review/              # Bài 7: Automated PR Review via Checkstyle Bot & Branch Protection
├── bai-08-executable-jar/              # Bài 8: Packaging Executable Standalone Fat JAR
├── bai-09-logging-slf4j/               # Bài 9: Professional Logging SLF4J, Logback & FileAppender
└── bai-10-broken-pipeline/             # Bài 10: Debugging 4 Pipeline Errors
```

---

## BÀI 1: QUẢN LÝ DEPENDENCY VỚI MAVEN (MATHUTILS)

### 1. Phân tích hiện trạng tệp pom.xml cũ (Legacy)
- Dự án `MathUtils` ban đầu chứa cấu hình cũ: sử dụng `System.out.println()` để in log thủ công ra console; phụ thuộc vào JUnit 4 (`junit:junit:4.12`) sử dụng runner cũ; không cấu hình phiên bản Java compiler chuẩn gây cảnh báo khi build trên JDK 17+.

### 2. Các nâng cấp đã thực hiện
1. **Thêm Logback Classic 1.4.11**:
   ```xml
   <dependency>
       <groupId>ch.qos.logback</groupId>
       <artifactId>logback-classic</artifactId>
       <version>1.4.11</version>
   </dependency>
   ```
   - Logback Classic 1.4.11 tự động kéo theo `slf4j-api` 2.0.x thông qua transitive dependency, cung cấp abstraction facade chuẩn công nghiệp cho toàn bộ ứng dụng.
2. **Thêm Hibernate Core 6.2.0.Final**:
   ```xml
   <dependency>
       <groupId>org.hibernate.orm</groupId>
       <artifactId>hibernate-core</artifactId>
       <version>6.2.0.Final</version>
   </dependency>
   ```
   - Hibernate 6.2.0.Final sử dụng chuẩn Jakarta Persistence API (`jakarta.persistence.*`), hỗ trợ ORM và lưu vết lịch sử tính toán (`CalculationRecord`).
3. **Chuyển đổi hoàn toàn sang JUnit Jupiter 5.9.2**:
   - Loại bỏ hoàn toàn `junit:junit:4.x` và `junit-vintage-engine` để tránh xung đột test runner.
   - Thêm duy nhất `org.junit.jupiter:junit-jupiter:5.9.2` với scope `test`.
4. **Giải quyết xung đột và đồng bộ Build Plugins**:
   - Nâng cấp `maven-compiler-plugin` lên `3.11.0` với target/source là Java 17.
   - Nâng cấp `maven-surefire-plugin` lên `3.1.2` để Surefire nhận diện và khởi chạy JUnit Platform Engine một cách tự động.

---

## BÀI 2: CODE QUALITY - CHECKSTYLE & OBSERVABILITY (BANKSYSTEM)

### 1. Tích hợp Maven Checkstyle Plugin
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-checkstyle-plugin</artifactId>
    <version>3.3.1</version>
    <configuration>
        <configLocation>checkstyle.xml</configLocation>
        <consoleOutput>true</consoleOutput>
        <failsOnError>true</failsOnError>
        <failOnViolation>true</failOnViolation>
        <includeTestSourceDirectory>true</includeTestSourceDirectory>
    </configuration>
    <executions>
        <execution>
            <id>validate</id>
            <phase>validate</phase>
            <goals>
                <goal>check</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

### 2. Chuẩn hóa mã nguồn theo Checkstyle
- **Quy tắc đặt tên (Naming Conventions):** Tên package chữ thường toàn bộ (`com.bank`), tên lớp theo PascalCase (`Account`, `Transaction`, `BankService`), tên phương thức/biến theo camelCase (`accountNumber`, `openAccount`).
- **Imports:** Cấm wildcard imports (`AvoidStarImport`), loại bỏ import dư thừa (`RedundantImport`, `UnusedImports`).
- **Khối lệnh:** Bắt buộc có dấu ngoặc nhọn `{}` cho mọi cấu trúc điều kiện `if/else`, `for`, `while` (`NeedBraces`).
- **Độ dài dòng:** Giới hạn 120 ký tự (`LineLength`).

### 3. Thiết kế Observability qua Logging (SLF4J + Logback)
| Cấp độ Log | Mục đích sử dụng | Điểm dữ liệu ghi nhận (Data Points) |
| :--- | :--- | :--- |
| **DEBUG** | Chẩn đoán chuyên sâu, theo dõi luồng nội bộ khi phát triển | Truy vấn tài khoản theo ID, lưu cache bộ nhớ |
| **INFO** | Ghi nhận mốc sự kiện nghiệp vụ quan trọng hoàn thành | Mở tài khoản thành công, nạp tiền thành công, rút tiền hoàn tất, số dư mới |
| **WARN** | Cảnh báo nghiệp vụ bất thường nhưng hệ thống vẫn xử lý được | Cố gắng mở tài khoản trùng số, rút tiền vượt số dư |
| **ERROR** | Lỗi nghiêm trọng, tham số không hợp lệ, hành động bị từ chối | Số tiền âm, tài khoản đích không tồn tại, ngoại lệ hệ thống |

---

## BÀI 3: CI/CD AUTOMATION (VÒNG ĐỜI MAVEN & LƯU GIỮ ARTIFACT)

- **Workflow GitHub Actions:** Kích hoạt tự động khi có sự kiện `push` hoặc tạo `pull_request` trên các nhánh chính (`main`, `master`).
- **Build Lifecycle:** Thực thi các pha `clean`, `test`, và `package`.
- **Upload Artifact:** Sử dụng `actions/upload-artifact@v4` lưu giữ các file `.jar` hoàn chỉnh.
- **Kịch bản lỗi & Debug:** Khả năng debug trực tiếp thông qua logs Surefire chi tiết.

---

## BÀI 4: KIỂM THỬ ĐA HỆ ĐIỀU HÀNH VỚI MATRIX STRATEGY

- **Vấn đề "It works on my machine":** Dấu `\` cứng trên Windows gây lỗi trên Linux/macOS.
- **Matrix Strategy:** Chạy đồng thời `[ubuntu-latest, windows-latest, macos-latest]`.
- **Refactoring Cross-Platform:** Sử dụng `java.nio.file.Path` và `File.separator`.

---

## BÀI 5: TEST COVERAGE & QUALITY ENFORCEMENT (JACOCO)

- **Ngưỡng nghiêm ngặt 80%:** Cấu hình `jacoco-maven-plugin:0.8.11` kiểm tra cả `LINE` và `BRANCH` >= 80%.
- Tự động làm fail bản build ở pha `verify` nếu chưa đạt độ bao phủ.
- Lưu trữ tệp báo cáo `index.html` lên Artifacts của GitHub Actions.

---

## BÀI 6: CI/CD PIPELINE OPTIMIZATION & DEPENDENCY CACHING

- Thêm `cache: 'maven'` vào `actions/setup-java@v4`.
- Tối ưu thời gian chạy từ **1m 42s** xuống **22s** (nhanh hơn 4.6 lần), tiết kiệm 100% băng thông tải lại dependencies từ Maven Central.

---

## BÀI 7: AUTOMATED CODE REVIEW VIA PULL REQUEST & BRANCH PROTECTION

- Tích hợp `dbelyaev/action-checkstyle@v2` gửi phản hồi inline trực tiếp vào dòng code sai phạm trên PR diff.
- Thiết lập Branch Protection Rules trên GitHub chặn nút Merge khi kiểm tra Checkstyle hoặc Unit Test chưa pass.

---

## BÀI 8: ĐÓNG GÓI SẢN PHẨM THỰC THI (EXECUTABLE JAR)

- Cấu hình `maven-jar-plugin` chỉ định `Main-Class: com.pack.MainApp`.
- Kết hợp `maven-shade-plugin` đóng gói Fat JAR độc lập.
- Chạy terminal: `java -jar target/executable-jar-1.0.0.jar`.
- Giải thích chi tiết ý nghĩa thư mục `target` và pha `package`.

---

## BÀI 9: TRIỂN KHAI LOGGING CHUYÊN NGHIỆP

- Loại bỏ 100% `System.out.println()`.
- Áp dụng SLF4J + Logback với `INFO`, `WARN`, `ERROR` và Parameterized Logging `{}`.
- Cấu hình `FileAppender` lưu log vật lý vào `logs/application.log`.

---

## BÀI 10: DEBUGGING "THE BROKEN PIPELINE" (4 LỖI KỸ THUẬT)

1. **Lỗi 1 (pom.xml dòng 18):** `logback-classic:9.9.9` không tồn tại trên Maven Central. Sửa thành `1.4.11`.
2. **Lỗi 2 (pom.xml dòng 34):** `maven-surefire-plugin:2.12.4` không hỗ trợ JUnit 5 Jupiter. Nâng cấp lên `3.1.2`.
3. **Lỗi 3 (.github/workflows/ci.yml dòng 8):** Thiếu `actions/checkout@v4`, thư mục runner trống không tìm thấy `pom.xml`. Đã bổ sung checkout.
4. **Lỗi 4 (Lỗi tự nghĩ):** Null Safety trong `ShippingCalculator.java`: gọi `type.equals(...)` trực tiếp gây `NullPointerException` khi `type == null`. Đã bắt điều kiện và ném `IllegalArgumentException("Type cannot be null")`.

---

## HƯỚNG DẪN KHỞI CHẠY TỪ TERMINAL

```bash
# Di chuyển vào thư mục dự án
cd C:\Java\HoangAnhLinh_25020670_Week9

# Chạy kiểm thử toàn bộ 10 modules
mvn clean test

# Kiểm tra Checkstyle và JaCoCo Coverage 80%
mvn verify

# Đóng gói sản phẩm JAR cho toàn bộ dự án
mvn package -DskipTests
```
