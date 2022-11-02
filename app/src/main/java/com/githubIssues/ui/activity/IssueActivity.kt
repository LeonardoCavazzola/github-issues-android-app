package com.githubIssues.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.githubIssues.model.Issue
import com.githubIssues.ui.viewmodel.IssueViewModel
import org.koin.android.ext.android.inject


class IssueActivity : ComponentActivity() {
    private val viewModel: IssueViewModel by inject()

    private fun renderIssue() = runOnUiThread {
        setContent { DefaultPreview(viewModel) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.issue = intent.getSerializableExtra(ISSUE) as Issue
        renderIssue()
    }

    companion object {
        private const val ISSUE = "issue"

        fun start(ctx: Context, issue: Issue) {
            val intent = Intent(ctx, IssueActivity::class.java).putExtra(ISSUE, issue)
            ctx.startActivity(intent)
        }
    }
}

@Composable
fun DefaultPreview(viewModel: IssueViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(viewModel.title) })
        },
        content = {
            Column(
                modifier = Modifier.fillMaxHeight().padding(all = 5.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                content = {
                    viewModel.body?.let { Text(it) }
                    Column {
                        Row {
                            Text(
                                text = "Status: ",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = viewModel.state,
                                fontSize = 20.sp
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            content = {
                                viewModel.userAvatar?.let {
                                    Image(
                                        bitmap = it.asImageBitmap(),
                                        contentDescription = null
                                    )
                                }
                                Column {
                                    Row {
                                        Text(
                                            text = "Usuário: ",
                                            fontSize = 17.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(text = viewModel.userLogin, fontSize = 20.sp)
                                    }
                                    Row {
                                        Text(
                                            text = "Data: ",
                                            fontSize = 17.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(text = viewModel.createdAt, fontSize = 20.sp)
                                    }
                                }
                            }
                        )
                    }
                }
            )
        }
    )
}
