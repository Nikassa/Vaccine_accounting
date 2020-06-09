module.exports = {
    extends: ['@commitlint/config-conventional'],
    rules: {
        // Проверка на пустые строки
        'body-leading-blank': [1, 'never'],
        // Тип изменения
        'type-case': [2, 'always', 'lower-case'],
        'type-empty': [2, 'never'],
        'type-enum': [
            2,
            'always',
            [
                'feat',
                'fix',
                'refactor',
                'style',
                'revert',
                'perf',
                'test'
            ]
        ],
        // Область изменений
        'scope-case': [2, 'always', 'lower-case'],
        // Краткое описание проделанной работы
        'subject-case': [
            2,
            'always',
            ['sentence-case'] // Сообщение должно начинаться с большой буквы
        ],
        'subject-empty': [2, 'never']
    }
};