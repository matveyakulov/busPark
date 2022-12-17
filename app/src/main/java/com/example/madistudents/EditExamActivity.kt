package com.example.madistudents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.*

class EditExamActivity : AppCompatActivity()
{
    private lateinit var editExamName: EditText
    private lateinit var editTeacherName: EditText
    private lateinit var editAuditory: EditText
    private lateinit var editDate: EditText
    private lateinit var editTime: EditText
    private lateinit var editPeople: EditText
    private lateinit var editAbstract: EditText
    private lateinit var editComment: EditText

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_exam)

        editExamName = findViewById(R.id.editTextExamName)
        editTeacherName = findViewById(R.id.editTextTeacherName)
        editAuditory = findViewById(R.id.editTextAuditory)
        editDate = findViewById(R.id.editTextDate)
        editTime = findViewById(R.id.editTextTime)
        editPeople = findViewById(R.id.editTextPeople)
        editAbstract = findViewById(R.id.editTextAbstract)
        editComment = findViewById(R.id.editTextComment)

        val action = intent.getSerializableExtra("action") as Int

        findViewById<Button>(R.id.button_confirm).setOnClickListener { confirmChanges(action) }

        if (action == 2)
        {
            editExamName.setText(intent.getSerializableExtra("exam") as String)
            editTeacherName.setText(intent.getSerializableExtra("teacher") as String)
            editAuditory.setText(intent.getSerializableExtra("auditory") as String)
            editDate.setText(intent.getSerializableExtra("date") as String)
            editTime.setText(intent.getSerializableExtra("time") as String)
            editPeople.setText(intent.getSerializableExtra("people") as String)
            if (intent.getSerializableExtra("abstract") as String == "1")
            {
                editAbstract.setText("можно")
            }
            else
            {
                editAbstract.setText("нельзя")
            }
            editComment.setText(intent.getSerializableExtra("comment") as String)
        }
    }

    private fun confirmChanges(action: Int)
    {
        if (editExamName.text.toString() != "" && editTeacherName.text.toString() != ""
            && editAuditory.text.toString() != "" && editDate.text.toString() != ""
            && editTime.text.toString() != "" && editPeople.text.toString() != ""
            && editAbstract.text.toString() != "")
        {
            if (editAbstract.text.toString().trim().lowercase(Locale.ROOT) == "можно"
                || editAbstract.text.toString().trim().lowercase(Locale.ROOT) == "нельзя")
            {
                val intent = Intent(this@EditExamActivity, MainActivity::class.java)
                intent.putExtra("action", action)
                intent.putExtra("exam", editExamName.text.toString().trim())
                intent.putExtra("teacher", editTeacherName.text.toString().trim())
                intent.putExtra("auditory", editAuditory.text.toString().trim())
                intent.putExtra("date", editDate.text.toString().trim())
                intent.putExtra("time", editTime.text.toString().trim())
                intent.putExtra("people", editPeople.text.toString().trim())
                intent.putExtra("abstract", editAbstract.text.toString().trim())
                intent.putExtra("comment", editComment.text.toString().trim())
                setResult(RESULT_OK, intent)
                finish()
            }
            else
            {
                val toast = Toast.makeText(
                    applicationContext,
                    "Поле \"Пользоваться лекциями\" поддерживает только " +
                            "значения \"можно\" или \"нельзя\"!",
                    Toast.LENGTH_SHORT
                )
                toast.show()
            }
        }
        else
        {
            val toast = Toast.makeText(
                applicationContext,
                "Заполните обязательные поля!",
                Toast.LENGTH_SHORT
            )
            toast.show()
        }
    }
}