package com.wilkins.showcase;

import org.junit.jupiter.api.Test;

import java.security.MessageDigest;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class HashingTest {

    @Test
    void canGetStringHashcode() {
        var str = "foo";
        assertThat(str.hashCode()).isEqualTo(101574);
        assertThat(Objects.hash(str)).isEqualTo(101605);
    }

    @Test
    void canGetObjectHashcode() {
        var obj = new Object();
        assertThat(obj.hashCode()).isEqualTo(58488213);
    }

    @Test
    void canHashString() throws Exception {
        var stringToHash = "foo";
        var messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(stringToHash.getBytes());
        var hashed = new String(messageDigest.digest());
        assertThat(hashed).isEqualTo(",&�kh�Ə��E<\u001D0A4\u0013B-pd�����^�bf�");
    }

    @Test
    void something() {

        var strings = new ConcurrentHashMap<String, String>();
        strings.put("foo", "BAR");
        var lowerFoo = strings.computeIfPresent("foo", (k, v) -> v.toLowerCase(Locale.ROOT));
        assertThat(lowerFoo).isEqualTo("bar");
    }

    @Test
    void canHashRecord() {
        var myFiesta = new Car("Ford", "Fiesta");
        var yourFiesta = new Car("Ford", "Fiesta");
        var escort = new Car("Ford", "Escort");

        assertThat(myFiesta.hashCode()).isEqualTo(myFiesta.hashCode());
        assertThat(myFiesta.hashCode()).isEqualTo(yourFiesta.hashCode());

        assertThat(myFiesta.hashCode()).isNotEqualTo(escort.hashCode());
        assertThat(escort.hashCode()).isEqualTo(escort.hashCode());

        assertThat(Objects.hash(myFiesta)).isNotEqualTo(myFiesta.hashCode());

        assertThat(Objects.hash(myFiesta)).isEqualTo(Objects.hash(yourFiesta));
    }

    @Test
    void canHashClass() {
        var van1 = new Van("Volkswagen", "Transporter");
        var van2 = new Van("Volkswagen", "Transporter");

        assertThat(van1).isEqualTo(van2);
        assertThat(van1.hashCode()).isEqualTo(van2.hashCode());
    }

    record Car(String make, String model) {
    }

    static class Van {
        String make;
        String model;

        public Van(String make, String model) {
            this.make = make;
            this.model = model;
        }
    }
}
