# 📦 Restful-booker API Tests

[//]: # ([![Build Status]&#40;https://github.com/natassssh/lesson_8_Restful_Booker/actions/workflows/runtests.yml/badge.svg&#41;]&#40;https://github.com/natassssh/lesson_8_Restful_Booker/actions&#41;)

[//]: # ([![Allure Report]&#40;https://img.shields.io/badge/Allure-Reportblueviolet?logo=allure&style=flat-square&#41;]&#40;https://natassssh.github.io/lesson_8_Restful_Booker/&#41;)



[![Build Status](https://github.com/natassssh/lesson_8_Restful_Booker/actions/workflows/run-tests.yml/badge.svg)](https://github.com/natassssh/lesson_8_Restful_Booker/actions)
[![Allure Report](https://img.shields.io/badge/Allure-Report-blueviolet?logo=allure&style=flat-square)](https://natassssh.github.io/lesson_8_Restful_Booker/)

---

Автоматизированные API тесты для [Restful Booker](https://restful-booker.herokuapp.com/) с использованием:
- **Java 17**
- **JUnit 5**
- **Rest-Assured**
- **Lombok**
- **Allure Reports**
- **GitHub Actions (CI)**

---

## 🚀 Как запустить тесты из консоли

```bash
mvn test -Dtest='com.restfulbooker.api.**'

```

---

## ⚙️ CI/CD Pipeline Overview

```mermaid
graph TD;
    Code[🧠 Push Code] --> Test[🧪 Run API Tests];
    Test --> Allure[📊 Generate Allure Report];
    Allure --> Pages[🌐 Publish to GitHub Pages];
    Test --> GH[🔁 Save History to Artifact];
    GH --> Allure;