package c.s.sample.contracts

import c.s.sample.constant.Constants
import org.fisco.bcos.web3j.crypto.Credentials
import org.fisco.bcos.web3j.protocol.Web3j
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt
import org.fisco.bcos.web3j.tuples.generated.Tuple2
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.math.BigInteger

@SpringBootTest
@RunWith(SpringRunner::class)
class TuitionTest {
    @Autowired
    lateinit var web3j: Web3j

    @Autowired
    lateinit var credentials: Credentials

    val tuition: Tuition by lazy {
        Tuition.load(Constants.Tuition.address, web3j, credentials,
                StaticGasProvider(Constants.Gas.PRICE, Constants.Gas.LIMIT)
        )
    }

    @Test
    fun payTest() {
        val tran: TransactionReceipt = tuition.pay("lisi", BigInteger.valueOf(5000)).send()
        println(tran.logs)
    }

    @Test
    fun appendFee() {
        val tran: TransactionReceipt = tuition.appendfee("lisi", BigInteger.valueOf(1000)).send()
        println(tran.logs)
    }

    @Test
    fun refund() {
        val tran: TransactionReceipt = tuition.refund("lisi").send()
        println(tran.logs)
    }

    @Test
    fun selectTest() {
        val tuple: Tuple2<BigInteger, BigInteger> = tuition.select("lisi").send()
        println(tuple.value1)
        println(tuple.value2)
    }

}