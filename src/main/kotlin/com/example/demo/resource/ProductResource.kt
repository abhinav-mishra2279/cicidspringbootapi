package com.example.demo.resource

import com.example.demo.dto.ProductRequest
import com.example.demo.service.ProductService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/products")
class ProductResource(
    private val productService: ProductService
) {
    @PostMapping
    fun create(@Valid @RequestBody request: ProductRequest) =
        ResponseEntity.status(HttpStatus.CREATED)
            .body(productService.create(request))
    @GetMapping
    fun getAll() = productService.getAll()
    @GetMapping("/{id}")
    fun get(@PathVariable id: UUID) = productService.getById(id)
    @PutMapping("/{id}")
    fun update(@PathVariable id: UUID,
                        @Valid @RequestBody request: ProductRequest) =
                    productService.updateById(id, request)
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID): ResponseEntity<Void>{
        productService.deleteById(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}