# BaumanEateries
Contest app for Bauman Best HACK'2019

Приложение Best HACK'2019(BaumanEateries) написано на языке Javа, как отборочное задание к хакатону Best HACK'2019.
Работает на устройствах с Android 5.0 и выше. Исходный код может быть открыт и скомплирован в актуальный на данный момент
версии Android Studio 3.3. Apk-файл и демо-видео работы приложения лежат в каталоге ./sample

Архитектура приложения представляет собой слабосвязанные paсkage'ы с фичами, и отдельным package core содержащим
родительские классы Activity, Fragment, ViewModel, Repository содержащие переисполльзуемый код, а также класс приложения.

Каждая фича находится в своем пакете и описывает одну цепочку связанных действий flow.
Всего в приложении 3 flow (Столовые, Блюда, Корзина).Flow holder выступает Activity не содержащее никакой логики 
и предоставляющее только контейнер для Fragment'ов. Единицей  flow (экраном) выступает Fragment. Fragment также не содержит
бизнес-логику и отвечает лишь за работу c UI. Вся логика сосредоточена ViewModel, каждая для своего фрагмента.
Фрагмент просит ViewModel предоставить данные и получает их виде колбэка через с помощью механизма RxJava.
Для предоставления  и хранения самих данных (например, заказа) ViewModel использует DataRepository.

Струтура проекта для наглядности представлена ниже:
![alt text](https://raw.githubusercontent.com/offthelightness/BaumanEateries/master/app/src/main/res/raw/structure.png?token=AUJnuoh9S3ECogQRZ1vBud22iyBXOnZqks5cm9sTwA%3D%3D)
