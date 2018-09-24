package com.ruslan.tinkoffcurrencyconverter;

/**
 * Интерфейс, описывающий базовый контракт между BaseView и BasePresenter.
 */
public interface BaseContract {

    /**
     * Интерфейс, описывающий базовый View.
     */
    interface BaseView {

        /**
         * Метод отображения сообщения об ошибке.
         * @param throwable Перехваченное исключение.
         */
        void showErrorMessage(Throwable throwable);
    }

    /**
     * Интерфейс, описывающий базовый Presenter.
     * @param <T> Тип View, который необходимо привязать или отвязать.
     */
    interface BasePresenter<T extends BaseView> {

        /**
         * Метод привязки элемента типа <code>T extends BaseView</code>.
         * @param t Привязываемый элемент типа <code>T extends BaseView</code>.
         */
        void attachView(T t);

        /**
         * Метод отвзяки элемента типа <code>T extends BaseView</code>.
         */
        void detachView();
    }
}
