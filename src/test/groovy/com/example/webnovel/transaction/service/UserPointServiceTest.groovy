package com.example.webnovel.transaction.service

import com.example.webnovel.persistence.User
import com.example.webnovel.persistence.UserRepository
import com.example.webnovel.transaction.dto.UserPointChargeResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification
import static org.mockito.Mockito.when

@SpringBootTest
@ActiveProfiles("test")
class UserPointServiceTest extends Specification {
    @MockBean
    UserRepository userRepository

    @Autowired
    UserPointService userPointService



    def "charge User Points"() {
        given:
        var user = new User(1, "test-user", 100)
        var pointToCharge = 300
        when(userRepository.findUserById(user.id)).thenReturn(Optional.of(user))

        when:
        UserPointChargeResponse userPointChargeResponse = userPointService.chargePoints(user.id, pointToCharge)

        then:
        userPointChargeResponse.getCurrentPoint() == 400
    }
}
