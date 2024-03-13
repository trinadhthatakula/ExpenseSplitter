import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import expensesplitter.composeapp.generated.resources.Res
import expensesplitter.composeapp.generated.resources.avatar_man_1
import model.Person
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.pow

@Composable
fun App() {
    val persons: MutableList<Person> = remember { mutableStateListOf() }
    MaterialTheme {
        Column(
            Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var newPersonName by remember {
                mutableStateOf("")
            }
            var totalMoneySpent by remember { mutableStateOf(persons.sumOf { it.total }) }
            var dividedExpenses by remember { mutableStateOf(totalMoneySpent / persons.size) }

            TextField(
                value = newPersonName,
                onValueChange = { newPersonName = it },
                label = { Text("Add New Person") },
                modifier = Modifier.padding(20.dp).width(200.dp)
            )
            Button(onClick = {
                if (newPersonName.isNotEmpty()) {
                    persons.add(Person(newPersonName))
                    totalMoneySpent = persons.sumOf { it.total }
                    dividedExpenses = totalMoneySpent / persons.size
                    newPersonName = ""
                }
            }) {
                Text("Add")
            }
            if (persons.isNotEmpty()) {
                Text("Persons")
                LazyRow {
                    items(persons) { person ->
                        PersonCard(person) {
                            persons[persons.indexOf(person)] = it
                            totalMoneySpent = persons.sumOf { person -> person.total }
                            dividedExpenses = totalMoneySpent / persons.size
                        }
                    }
                }

                Text("Total Money Spent: ₹ ${totalMoneySpent}/-")
                persons.forEachIndexed { index, it ->
                    val amountOwed = dividedExpenses - it.total
                    it.moneyOwed = amountOwed
                    persons[index] = it
                }

                val positivePersons = persons.filter { it.moneyOwed < 0 }
                val negativePersons = persons.filter { it.moneyOwed > 0 }

                positivePersons.forEach { positivePerson ->
                    negativePersons.forEach { negativePerson ->
                        val amountOwed = dividedExpenses - negativePerson.total
                        Text("${negativePerson.name} owes ${positivePerson.name} ₹ ${(amountOwed / positivePersons.size).addDecimals()}/-")
                    }
                }

            }

        }
    }
}


@OptIn(ExperimentalResourceApi::class)
@Preview
@Composable
fun PersonCard(
    person: Person = Person("John Doe", mutableListOf(100.0, 200.0, 300.0), 600.0),
    onExpenseAdded: (Person) -> Unit = {}
) {

    ElevatedCard(modifier = Modifier.padding(5.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painterResource(Res.drawable.avatar_man_1), null,
                modifier = Modifier.padding(20.dp).size(200.dp).clip(CircleShape)
            )
            Text(person.name)
            var total by remember { mutableStateOf(person.total) }
            Text("₹ ${total}/-")
            var newExpense by remember { mutableStateOf("") }
            TextField(
                value = newExpense,
                onValueChange = {
                    newExpense = it
                },
                label = { Text("Add expense") },
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp)
                    .width(200.dp)
            )
            Button(onClick = {
                print("newExpense = $newExpense")
                total += newExpense.toDouble()
                person.total = total
                person.expenses.add(newExpense.toDouble())
                onExpenseAdded(person)
                newExpense = ""
            }) {
                Text("Add")
            }

        }
    }

}


fun Number.addDecimals(maxDecimals: Int = 2): String {
    return if (this.toString().contains(".")) {
        ((this.toDouble() * (10.0.pow(maxDecimals))).toInt() / 10.0.pow(maxDecimals)).toString()
    } else {
        var result = this.toString()
        repeat(maxDecimals) {
            result += "0"
        }
        result
    }
}