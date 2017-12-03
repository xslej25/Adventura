/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import logika.Kufr;

/**
 *
 * @author JanSlechta
 */
public interface SubjektZmenyKufru {
    public void zaregistrujPozorovatele(ObserverZmenyKufru pozorovatel);
    public void odregistrujPozorovatele(ObserverZmenyKufru pozorovatel);
    public void upozorniPozorovateleKufru(Kufr kufr);
}
