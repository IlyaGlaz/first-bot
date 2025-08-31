package ru.iglaz.firstbot

import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.newChatMembers
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.ParseMode

const val TOKEN = "7471887238:AAGKYITUiZtzsaHdQwgFcZFEO4HIw-3p6iY"

fun main() {
    val bot = bot {
        token = TOKEN
        dispatch {
            newChatMembers {
                val chatId = message.chat.id
                val newMembers = message.newChatMembers ?: emptyList()

                newMembers.forEach { user ->
                    if (!user.isBot) {

                        val welcomeMessage = """
                            🎲 Добро пожаловать в клуб настольных варгеймов, ${user.firstName}! 🎲
                            
                            Ты попал(а) в логово стратегов и тактиков! Здесь мы:
                            • ⚔️ Организуем игровые встречи
                            • 📚 Делимся опытом и стратегиями
                            • 🤝 Общаяемся о жизни
        
                            Расскажи немного о себе и своих любимых играх! 🎪
                            
                            Здесь информция о других участниках группы, можешь тоже заполнить:
                            https://docs.google.com/spreadsheets/d/1VqgLLzhQwADY4fMzal9cYtluvRmkg6q9M7NqyYJBIqs/edit?usp=sharing
                        """.trimIndent()

                        bot.sendMessage(
                            chatId = ChatId.fromId(chatId),
                            text = welcomeMessage,
                            parseMode = ParseMode.HTML
                        )

                        println("Новый участник: ${user.firstName} в чате: ${message.chat.title}")
                    }
                }
            }
        }
    }
    bot.startPolling()

}