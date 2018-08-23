package unit.com.sds.act.coe.user.service;

import com.sds.act.coe.user.message.Sender;
import com.sds.act.coe.user.repository.UserRepository;
import com.sds.act.coe.user.repository.entity.UserEntity;
import com.sds.act.coe.user.service.UserService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserEntityServiceTest {

    private UserService userService;

    @Mock
    private UserRepository mockCustomerRepository;

    @Mock
    private Sender mockSender;

    @Before
    public void setUp() {
        userService = new UserService(mockCustomerRepository, mockSender);
    }

    @Test
    @Ignore
    public void givenNewCustomerIsNotExist_whenRegisterNewCustomer_thenCallSaveAndSendNewEmail() {
        //GIVEN
        given(mockCustomerRepository.findByName(eq("New UserEntity"))).willReturn(Optional.empty());
        UserEntity userEntity = new UserEntity("New UserEntity", "newEmail@samsung.com");

        //WHEN
        userService.register(userEntity);

        //THEN
        then(mockCustomerRepository).should().save(eq(userEntity));
        then(mockSender).should().send(eq(userEntity.getEmail()));
    }

    @Test(expected = RuntimeException.class)
    public void givenNewCustomerIsAlready_whenRegisterNewCustomer_thenThrowException() {
        //GIVEN
        UserEntity userEntity = new UserEntity("New UserEntity", "newEmail@samsung.com");
        given(mockCustomerRepository.findByName(eq("New UserEntity"))).willReturn(Optional.of(userEntity));

        //WHEN
        userService.register(userEntity);
    }

    @Test
    public void whenGetAllCustomer_thenCall1TimeRepository() {
        //WHEN
        userService.search();

        //THEN
        then(mockCustomerRepository).should().findAll();
    }

    @Test
    public void whenGetACustomerById_thenCall1TimeRepository() {
        //GIVEN
        UserEntity userEntity = new UserEntity("New UserEntity", "newEmail@samsung.com");
        userEntity.setUserId(1);
        given(mockCustomerRepository.getOne(eq(1))).willReturn(userEntity);

        //WHEN
        userService.get(1);

        //THEN
        then(mockCustomerRepository).should().getOne(eq(1));
    }
}