package unit.com.sds.act.coe.customer.service;

import com.sds.act.coe.customer.domain.Customer;
import com.sds.act.coe.customer.message.Sender;
import com.sds.act.coe.customer.repository.CustomerRepository;
import com.sds.act.coe.customer.service.CustomerService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.eq;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerServiceTest {

    private CustomerService customerService;

    @Mock
    private CustomerRepository mockCustomerRepository;

    @Mock
    private Sender mockSender;

    @Before
    public void setUp() {
        customerService = new CustomerService(mockCustomerRepository, mockSender);
    }

    @Test
    @Ignore
    public void givenNewCustomerIsNotExist_whenRegisterNewCustomer_thenCallSaveAndSendNewEmail() {
        //GIVEN
        given(mockCustomerRepository.findByName(eq("New Customer"))).willReturn(Optional.empty());
        Customer customer = new Customer("New Customer", "newEmail@samsung.com");

        //WHEN
        customerService.register(customer);

        //THEN
        then(mockCustomerRepository).should().save(eq(customer));
        then(mockSender).should().send(eq(customer.getEmail()));
    }

    @Test(expected = RuntimeException.class)
    public void givenNewCustomerIsAlready_whenRegisterNewCustomer_thenThrowException() {
        //GIVEN
        Customer customer = new Customer("New Customer", "newEmail@samsung.com");
        given(mockCustomerRepository.findByName(eq("New Customer"))).willReturn(Optional.of(customer));

        //WHEN
        customerService.register(customer);
    }

    @Test
    public void whenGetAllCustomer_thenCall1TimeRepository() {
        //WHEN
        customerService.getAll();

        //THEN
        then(mockCustomerRepository).should().findAll();
    }

    @Test
    public void whenGetACustomerById_thenCall1TimeRepository() {
        //GIVEN
        Customer customer = new Customer("New Customer", "newEmail@samsung.com");
        customer.setCustomerId(1);
        given(mockCustomerRepository.getOne(eq(1))).willReturn(customer);

        //WHEN
        customerService.get(1);

        //THEN
        then(mockCustomerRepository).should().getOne(eq(1));
    }
}