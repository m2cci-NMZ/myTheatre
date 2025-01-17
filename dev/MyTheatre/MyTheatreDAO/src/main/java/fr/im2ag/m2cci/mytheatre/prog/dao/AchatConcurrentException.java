/*
 * Copyright (C) 2018 genoud
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.im2ag.m2cci.mytheatre.prog.dao;

/**
 * Exception  lancée lorsqu'un achat concurrent a lieu. C'est à dire lorsqu'un 
 * utilisateur essaye d'acheter des places alors qu'elles ont entre temps
 * été achetées par quelqu'un d'autre.
 * 
 * @author Philippe GENOUD - Université Grenoble Alpes - LIG STeamer
 */
public class AchatConcurrentException extends Exception {

    public AchatConcurrentException() {
        super();
    }
    
    public AchatConcurrentException(String mess) {
        super(mess);
    }
    
    public AchatConcurrentException(String mess, Throwable cause) {
        super(mess,cause);
    }
    

}
